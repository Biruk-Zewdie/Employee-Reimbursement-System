package com.biruk.ERS.Controllers;

import com.biruk.ERS.DTOs.ReimbursementDTO;
import com.biruk.ERS.Services.ReimbursementService;
import com.biruk.ERS.models.ReimbursementClaim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reimbursements")
@CrossOrigin
public class ReimbursementController {

    private final ReimbursementService reimbursementService;

    @Autowired
    public ReimbursementController (ReimbursementService reimbursementService){
        this.reimbursementService = reimbursementService;
    }

    @PostMapping
    public ResponseEntity<ReimbursementClaim> createReimbursement (@RequestBody ReimbursementDTO reimbursementDTO) {

        return ResponseEntity.accepted().body(reimbursementService.createReimbursement(reimbursementDTO));

    }

    //get reimbursements by userId


}
