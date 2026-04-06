package offeria.proposal_service.application.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Data Transfer Object for generating a Commercial Proposal.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommercialProposalRequest {

    @NotBlank(message = "Commercial details are mandatory")
    private String commercialDetails;

    @NotNull(message = "Estimated value is mandatory")
    @DecimalMin(value = "0.0", inclusive = false, message = "Estimated value must be greater than zero")
    private BigDecimal estimatedValue;
}
