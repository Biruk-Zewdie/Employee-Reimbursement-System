package com.biruk.ERS.Services;

import com.biruk.ERS.DAOs.ReimbursementDAO;
import com.biruk.ERS.DAOs.UserDAO;
import com.biruk.ERS.DTOs.ReimbursementDTO;
import com.biruk.ERS.DTOs.UserDTO;
import com.biruk.ERS.models.ReimbursementClaim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReimbursementService {

    private final UserDAO userDAO;
    private final ReimbursementDAO reimbursementDAO;

    @Autowired
    public ReimbursementService (UserDAO userDAO, ReimbursementDAO reimbursementDAO){
        this.userDAO = userDAO;
        this.reimbursementDAO = reimbursementDAO;

    }

//    public ReimbursementClaim createReimbursement (ReimbursementDTO reimbursementDTO){
//
//        ReimbursementClaim newClaim = new ReimbursementClaim(
//
//        );
//
//    }


}
