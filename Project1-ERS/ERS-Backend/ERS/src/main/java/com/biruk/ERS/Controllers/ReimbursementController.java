package com.biruk.ERS.Controllers;

import com.biruk.ERS.DTOs.ReimbursementDTO;
import com.biruk.ERS.Services.ReimbursementService;
import com.biruk.ERS.models.ReimbursementClaim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reimbursements")
@CrossOrigin
public class ReimbursementController {

    private final ReimbursementService reimbursementService;

    @Autowired
    public ReimbursementController (ReimbursementService reimbursementService){
        this.reimbursementService = reimbursementService;
    }

    //Create a new reimbursement request
    @PostMapping
    public ResponseEntity<ReimbursementClaim> createReimbursement (@RequestBody ReimbursementDTO reimbursementDTO) {

        return ResponseEntity.accepted().body(reimbursementService.createReimbursement(reimbursementDTO));

    }

    //get all reimbursements (only for managers)
    @GetMapping
    public ResponseEntity<List<ReimbursementDTO>> getAllReimbursements () {

        List<ReimbursementDTO> reimbursementClaims = reimbursementService.getAllReimbursements();

        return ResponseEntity.ok(reimbursementClaims);

    }

    @GetMapping("/{requestId}")
    public ResponseEntity<ReimbursementDTO> getReimbursementByRequestId (@PathVariable int requestId){
        ReimbursementDTO reimbursementDTO = reimbursementService.getReimbursementById(requestId);
        return ResponseEntity.ok(reimbursementDTO);
    }

    @GetMapping("/pending")
    public ResponseEntity<List<ReimbursementDTO>> getPendingReimbursements () {
        List<ReimbursementDTO> pendingReimbursements = reimbursementService.getPendingReimbursements();

        return ResponseEntity.ok(pendingReimbursements);
    }


}
