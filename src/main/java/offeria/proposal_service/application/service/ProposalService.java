package offeria.proposal_service.application.service;

import offeria.proposal_service.application.dto.CommercialProposalRequest;
import offeria.proposal_service.application.dto.ProposalResponseDTO;
import offeria.proposal_service.application.dto.TechnicalProposalRequest;

import java.util.List;
import java.util.UUID;

/**
 * Service interface defining the business operations for Proposals.
 */
public interface ProposalService {

    /**
     * Creates a new technical proposal.
     * @param request data for the technical proposal
     * @param createdBy the username of the creator
     * @return the created proposal DTO
     */
    ProposalResponseDTO createTechnicalProposal(TechnicalProposalRequest request, String createdBy);

    /**
     * Approves an existing technical proposal.
     * @param id UUID of the proposal
     * @return the updated proposal DTO
     */
    ProposalResponseDTO approveProposal(UUID id);

    /**
     * Generates the commercial part of an approved proposal.
     * @param id UUID of the proposal
     * @param request commercial details and value
     * @return the updated proposal DTO
     */
    ProposalResponseDTO generateCommercialProposal(UUID id, CommercialProposalRequest request);

    /**
     * Retrieves a proposal by its ID.
     * @param id UUID of the proposal
     * @return the proposal DTO
     */
    ProposalResponseDTO getProposalById(UUID id);

    /**
     * Retrieves all proposals.
     * @return list of proposal DTOs
     */
    List<ProposalResponseDTO> getAllProposals();
}
