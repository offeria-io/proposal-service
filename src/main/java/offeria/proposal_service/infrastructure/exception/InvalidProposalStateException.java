package offeria.proposal_service.infrastructure.exception;

/**
 * Exception thrown when a state transition is not allowed for the current proposal state.
 */
public class InvalidProposalStateException extends RuntimeException {
    public InvalidProposalStateException(String message) {
        super(message);
    }
}
