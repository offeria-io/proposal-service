package offeria.proposal_service.application.mapper;

import offeria.proposal_service.application.dto.ProposalResponseDTO;
import offeria.proposal_service.application.dto.TechnicalProposalRequest;
import offeria.proposal_service.domain.model.Proposal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * Mapper interface using MapStruct for converting between Entities and DTOs.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProposalMapper {

    /**
     * Converts a TechnicalProposalRequest to a Proposal Entity.
     * @param request the request DTO
     * @return the Proposal entity
     */
    @Mapping(target = "status", constant = "TECHNICAL_GENERATED")
    Proposal toEntity(TechnicalProposalRequest request);

    /**
     * Converts a Proposal Entity to a ProposalResponseDTO.
     * @param proposal the entity
     * @return the response DTO
     */
    ProposalResponseDTO toResponseDTO(Proposal proposal);

    /**
     * Converts a list of Proposal Entities to a list of ProposalResponseDTOs.
     * @param proposals list of entities
     * @return list of response DTOs
     */
    List<ProposalResponseDTO> toResponseDTOList(List<Proposal> proposals);
}
