package com.biruk.ERS.Services;

import com.biruk.ERS.DAOs.ReimbursementDAO;
import com.biruk.ERS.DAOs.UserDAO;
import com.biruk.ERS.DTOs.ReimbursementDTO;
import com.biruk.ERS.DTOs.UserDTO;
import com.biruk.ERS.models.ReimbursementClaim;
import com.biruk.ERS.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReimbursementService {

    private final UserDAO userDAO;
    private final ReimbursementDAO reimbursementDAO;

    @Autowired
    public ReimbursementService (UserDAO userDAO, ReimbursementDAO reimbursementDAO){
        this.userDAO = userDAO;
        this.reimbursementDAO = reimbursementDAO;

    }

    //Create a reimbursement
    public ReimbursementClaim createReimbursement (ReimbursementDTO reimbursementDTO){

        ReimbursementClaim newClaim = new ReimbursementClaim(
                0,
                null,
                reimbursementDTO.getAmount(),
                reimbursementDTO.getDescription(),
                null,
                null
        );

        Optional<User> user = userDAO.findById(reimbursementDTO.getUserId());

        //if the user doesn't exist, it will be empty
        if (user.isEmpty()){
            throw new IllegalArgumentException("user doesn't exist!");
        }else {
            newClaim.setUser(user.get());
        }

        return reimbursementDAO.save(newClaim);

    }

    //get reimbursement claim by Id

}
