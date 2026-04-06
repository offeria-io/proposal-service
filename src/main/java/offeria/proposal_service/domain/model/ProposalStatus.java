package offeria.proposal_service.domain.model;

/**
 * Enumeration of the different states a proposal can be in.
 * Represents the workflow lifecycle of a proposal.
 */
public enum ProposalStatus {
    /** Initial state when a technical proposal is created */
    TECHNICAL_GENERATED,
    
    /** State after the technical proposal has been reviewed and approved */
    APPROVED,
    
    /** State when a commercial proposal has been generated based on an approved technical one */
    COMMERCIAL_GENERATED,
    
    /** State if the proposal is rejected or cancelled */
    REJECTED
}
