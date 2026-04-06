package offeria.proposal_service.infrastructure.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import offeria.proposal_service.application.dto.ProposalResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Implementation of ProposalEventProducer for Kafka.
 * Manages topic names and event distribution.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProposalEventProducer implements ProposalEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Value("${app.kafka.topics.technical-generated}")
    private String technicalGeneratedTopic;

    @Value("${app.kafka.topics.commercial-generated}")
    private String commercialGeneratedTopic;

    @Override
    public void sendTechnicalGenerated(ProposalResponseDTO proposal) {
        publishEvent(technicalGeneratedTopic, proposal);
    }

    @Override
    public void sendCommercialGenerated(ProposalResponseDTO proposal) {
        publishEvent(commercialGeneratedTopic, proposal);
    }

    /**
     * Helper method to serialize and send an event.
     */
    private void publishEvent(String topic, ProposalResponseDTO payload) {
        try {
            String message = objectMapper.writeValueAsString(payload);
            log.info("Publishing event to topic {}: {}", topic, message);
            kafkaTemplate.send(topic, payload.getId().toString(), message);
        } catch (JsonProcessingException e) {
            log.error("Failed to serialize proposal event", e);
            throw new RuntimeException("Event serialization error", e);
        }
    }
}
