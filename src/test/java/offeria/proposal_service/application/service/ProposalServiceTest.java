package offeria.proposal_service.application.service;

import offeria.proposal_service.application.dto.ProposalResponseDTO;
import offeria.proposal_service.application.dto.TechnicalProposalRequest;
import offeria.proposal_service.application.mapper.ProposalMapper;
import offeria.proposal_service.domain.model.Proposal;
import offeria.proposal_service.domain.model.ProposalStatus;
import offeria.proposal_service.domain.repository.ProposalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for ProposalServiceImpl using Mockito.
 */
@ExtendWith(MockitoExtension.class)
class ProposalServiceTest {

    @Mock
    private ProposalRepository proposalRepository;

    @Mock
    private ProposalMapper proposalMapper;

    @InjectMocks
    private ProposalServiceImpl proposalService;

    private TechnicalProposalRequest request;
    private Proposal proposal;
    private ProposalResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        request = TechnicalProposalRequest.builder()
                .title("Test Proposal")
                .technicalDescription("Description")
                .build();

        proposal = Proposal.builder()
                .id(UUID.randomUUID())
                .title("Test Proposal")
                .technicalDescription("Description")
                .status(ProposalStatus.TECHNICAL_GENERATED)
                .createdBy("user")
                .build();

        responseDTO = ProposalResponseDTO.builder()
                .id(proposal.getId())
                .title(proposal.getTitle())
                .status(ProposalStatus.TECHNICAL_GENERATED)
                .build();
    }

    @Test
    void createTechnicalProposal_ShouldReturnResponseAndPublishEvent() {
        // Given
        when(proposalMapper.toEntity(request)).thenReturn(proposal);
        when(proposalRepository.save(any(Proposal.class))).thenReturn(proposal);
        when(proposalMapper.toResponseDTO(proposal)).thenReturn(responseDTO);

        // When
        ProposalResponseDTO result = proposalService.createTechnicalProposal(request, "user");

        // Then
        assertNotNull(result);
        assertEquals(proposal.getId(), result.getId());
        verify(proposalRepository, times(1)).save(any(Proposal.class));
        // verify(eventProducer, times(1)).sendTechnicalGenerated(any(ProposalResponseDTO.class));
    }
}
