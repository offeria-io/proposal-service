package offeria.proposal_service.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entity representing a Proposal in the system.
 * This class follows JPA specifications for database mapping.
 */
@Entity
@Table(name = "proposals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String technicalDescription;

    @Column(columnDefinition = "TEXT")
    private String commercialDetails;

    @Column(precision = 19, scale = 4)
    private BigDecimal estimatedValue;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProposalStatus status;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * Helper method to transition the state to APPROVED.
     */
    public void approve() {
        if (this.status != ProposalStatus.TECHNICAL_GENERATED) {
            throw new IllegalStateException("Only technical proposals can be approved.");
        }
        this.status = ProposalStatus.APPROVED;
    }

    /**
     * Helper method to transition the state to COMMERCIAL_GENERATED.
     * @param details commercial details for the proposal
     * @param value commercial value
     */
    public void generateCommercial(String details, BigDecimal value) {
        if (this.status != ProposalStatus.APPROVED) {
            throw new IllegalStateException("Proposal must be approved before generating commercial part.");
        }
        this.commercialDetails = details;
        this.estimatedValue = value;
        this.status = ProposalStatus.COMMERCIAL_GENERATED;
    }
}
