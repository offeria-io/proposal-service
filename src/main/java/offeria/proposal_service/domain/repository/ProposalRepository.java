package offeria.proposal_service.domain.repository;

import offeria.proposal_service.domain.model.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for managing Proposals in the database.
 * Inherits CRUD operations from JpaRepository.
 */
@Repository
public interface ProposalRepository extends JpaRepository<Proposal, UUID> {
}
