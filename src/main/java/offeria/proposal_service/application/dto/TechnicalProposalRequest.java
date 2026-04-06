package offeria.proposal_service.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for creating a Technical Proposal.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TechnicalProposalRequest {

    @NotBlank(message = "Proposal title is mandatory")
    private String title;

    @NotBlank(message = "Technical description is mandatory")
    private String technicalDescription;
}
