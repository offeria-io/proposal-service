package offeria.proposal_service.infrastructure.exception;

/**
 * Exception thrown when a requested proposal is not found in the database.
 */
public class ProposalNotFoundException extends RuntimeException {
    public ProposalNotFoundException(String message) {
        super(message);
    }
}
