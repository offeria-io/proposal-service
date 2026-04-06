package offeria.proposal_service.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import offeria.proposal_service.application.dto.CommercialProposalRequest;
import offeria.proposal_service.application.dto.ProposalResponseDTO;
import offeria.proposal_service.application.dto.TechnicalProposalRequest;
import offeria.proposal_service.application.mapper.ProposalMapper;
import offeria.proposal_service.domain.model.Proposal;
import offeria.proposal_service.domain.repository.ProposalRepository;
import offeria.proposal_service.infrastructure.exception.ProposalNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Implementation of ProposalService using layered architecture principles.
 * Handles transaction management and integration with the repository and messaging.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ProposalServiceImpl implements ProposalService {

    private final ProposalRepository proposalRepository;
    private final ProposalMapper proposalMapper;

    @Override
    @Transactional
    public ProposalResponseDTO createTechnicalProposal(TechnicalProposalRequest request, String createdBy) {
        log.info("Creating technical proposal: {}", request.getTitle());
        
        // Map DTO to entity
        Proposal proposal = proposalMapper.toEntity(request);
        proposal.setCreatedBy(createdBy);
        
        // Save to database
        Proposal savedProposal = proposalRepository.save(proposal);
        
        // Map back to response DTO
        ProposalResponseDTO response = proposalMapper.toResponseDTO(savedProposal);
        
        // Publish Kafka event (Temporarily disabled)
        // eventProducer.sendTechnicalGenerated(response);
        
        return response;
    }

    @Override
    @Transactional
    public ProposalResponseDTO approveProposal(UUID id) {
        log.info("Approving proposal with ID: {}", id);
        
        Proposal proposal = findProposalById(id);
        proposal.approve();
        
        Proposal updatedProposal = proposalRepository.save(proposal);
        return proposalMapper.toResponseDTO(updatedProposal);
    }

    @Override
    @Transactional
    public ProposalResponseDTO generateCommercialProposal(UUID id, CommercialProposalRequest request) {
        log.info("Generating commercial proposal for ID: {}", id);
        
        Proposal proposal = findProposalById(id);
        proposal.generateCommercial(request.getCommercialDetails(), request.getEstimatedValue());
        
        Proposal updatedProposal = proposalRepository.save(proposal);
        ProposalResponseDTO response = proposalMapper.toResponseDTO(updatedProposal);
        
        // Publish Kafka event (Temporarily disabled)
        // eventProducer.sendCommercialGenerated(response);
        
        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public ProposalResponseDTO getProposalById(UUID id) {
        return proposalMapper.toResponseDTO(findProposalById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProposalResponseDTO> getAllProposals() {
        return proposalMapper.toResponseDTOList(proposalRepository.findAll());
    }

    /**
     * Helper method to find a proposal or throw a specialized exception.
     */
    private Proposal findProposalById(UUID id) {
        return proposalRepository.findById(id)
                .orElseThrow(() -> new ProposalNotFoundException("Proposal not found with id: " + id));
    }
}
