package offeria.proposal_service.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import offeria.proposal_service.domain.model.ProposalStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Data Transfer Object for responding with Proposal details.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProposalResponseDTO {
    private UUID id;
    private String title;
    private String technicalDescription;
    private String commercialDetails;
    private BigDecimal estimatedValue;
    private ProposalStatus status;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
