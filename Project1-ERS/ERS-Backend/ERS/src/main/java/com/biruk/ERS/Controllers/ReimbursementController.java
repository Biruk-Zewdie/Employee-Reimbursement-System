package com.biruk.ERS.Controllers;

import com.biruk.ERS.Aspects.ManagerOnly;
import com.biruk.ERS.DTOs.ReimbursementDTO;
import com.biruk.ERS.Services.ReimbursementService;
import com.biruk.ERS.models.ReimbursementClaim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reimbursements")
@CrossOrigin(value = "http://localhost:5173", allowCredentials = "true")
public class ReimbursementController {

    private final ReimbursementService reimbursementService;

    @Autowired
    public ReimbursementController (ReimbursementService reimbursementService){
        this.reimbursementService = reimbursementService;
    }

    //Create a new reimbursement request
    @PostMapping
    public ResponseEntity<ReimbursementDTO> createReimbursement (@RequestBody ReimbursementDTO reimbursementDTO) {
            ReimbursementClaim createdClaim = reimbursementService.createReimbursement(reimbursementDTO);
            ReimbursementDTO responseDTO = new ReimbursementDTO(createdClaim);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

    }

    //get all reimbursements (only for managers)
    @GetMapping
    @ManagerOnly
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
    @ManagerOnly
    public ResponseEntity<List<ReimbursementDTO>> getPendingReimbursements () {
        List<ReimbursementDTO> pendingReimbursements = reimbursementService.getPendingReimbursements();

        return ResponseEntity.ok(pendingReimbursements);
    }


    @PatchMapping("/{requestId}/status")
    @ManagerOnly
    public ResponseEntity<ReimbursementDTO> updateStatus (@PathVariable int requestId, @RequestParam ReimbursementClaim.ReimbursementStatus status){

        ReimbursementDTO updateClaim = reimbursementService.updateReimbursementStatus(requestId, status);

        return  ResponseEntity.ok(updateClaim);
    }

    @DeleteMapping("/{requestId}")
    @ManagerOnly
    public ResponseEntity<String> deleteReimbursement (@PathVariable int requestId){

        reimbursementService.deleteReimbursement(requestId);
        return  ResponseEntity.ok("User deleted successfully!");

    }

    @GetMapping("/user/{userId}")
    public  ResponseEntity<List<ReimbursementDTO>> getReimbursementByUserId ( @PathVariable int userId) {

        List <ReimbursementDTO> reimbursements = reimbursementService.getReimbursementByUserId(userId);

        return ResponseEntity.ok(reimbursements);

    }


}
