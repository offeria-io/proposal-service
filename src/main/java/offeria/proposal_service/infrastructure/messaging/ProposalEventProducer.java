package offeria.proposal_service.infrastructure.messaging;

import offeria.proposal_service.application.dto.ProposalResponseDTO;
import org.springframework.stereotype.Component;

/**
 * Service to publish proposal events to Kafka.
 */
@Component
public interface ProposalEventProducer {

    /**
     * Publishes a technical generated event.
     * @param proposal proposal details
     */
    void sendTechnicalGenerated(ProposalResponseDTO proposal);

    /**
     * Publishes a commercial generated event.
     * @param proposal proposal details
     */
    void sendCommercialGenerated(ProposalResponseDTO proposal);
}
