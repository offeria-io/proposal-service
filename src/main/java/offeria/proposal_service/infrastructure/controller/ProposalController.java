package offeria.proposal_service.infrastructure.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import offeria.proposal_service.application.dto.CommercialProposalRequest;
import offeria.proposal_service.application.dto.ProposalResponseDTO;
import offeria.proposal_service.application.dto.TechnicalProposalRequest;
import offeria.proposal_service.application.service.ProposalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * REST Controller for Managing Proposal lifecycle.
 * Exposes API endpoints for technical and commercial proposal generation.
 */
@RestController
@RequestMapping("/api/v1/proposals")
@RequiredArgsConstructor
public class ProposalController {

    private final ProposalService proposalService;

    /**
     * Endpoint to create a technical proposal.
     */
    @PostMapping("/technical")
    public ResponseEntity<ProposalResponseDTO> createTechnical(@Valid @RequestBody TechnicalProposalRequest request) {
        // In a real scenario, the username would come from the security context
        String createdBy = "system-user";
        return new ResponseEntity<>(proposalService.createTechnicalProposal(request, createdBy), HttpStatus.CREATED);
    }

    /**
     * Endpoint to approve a technical proposal.
     */
    @PatchMapping("/{id}/approve")
    public ResponseEntity<ProposalResponseDTO> approve(@PathVariable UUID id) {
        return ResponseEntity.ok(proposalService.approveProposal(id));
    }

    /**
     * Endpoint to generate a commercial proposal for an approved technical one.
     */
    @PostMapping("/{id}/commercial")
    public ResponseEntity<ProposalResponseDTO> generateCommercial(@PathVariable UUID id, @Valid @RequestBody CommercialProposalRequest request) {
        return ResponseEntity.ok(proposalService.generateCommercialProposal(id, request));
    }

    /**
     * Endpoint to fetch a single proposal by ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProposalResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(proposalService.getProposalById(id));
    }

    /**
     * Endpoint to list all proposals.
     */
    @GetMapping
    public ResponseEntity<List<ProposalResponseDTO>> getAll() {
        return ResponseEntity.ok(proposalService.getAllProposals());
    }
}
