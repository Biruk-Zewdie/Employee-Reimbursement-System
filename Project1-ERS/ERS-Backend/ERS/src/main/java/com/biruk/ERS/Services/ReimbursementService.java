package com.biruk.ERS.Services;

import com.biruk.ERS.DAOs.ReimbursementDAO;
import com.biruk.ERS.DAOs.UserDAO;
import com.biruk.ERS.DTOs.ReimbursementDTO;
import com.biruk.ERS.DTOs.UserDTO;
import com.biruk.ERS.models.ReimbursementClaim;
import com.biruk.ERS.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

        System.out.println(reimbursementDTO);

        ReimbursementClaim newClaim = new ReimbursementClaim(
                0,
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

    //Get all reimbursements claims

    public List<ReimbursementDTO> getAllReimbursements () {

        List<ReimbursementClaim> returnedReimbursements = reimbursementDAO.findAll();

        //convert the reimbursements into List of Reimbursement DTOs
        List<ReimbursementDTO> reimbursementDTOs = new ArrayList<>();

        for (int i = 0; i < returnedReimbursements.size(); i++){
            ReimbursementClaim r = returnedReimbursements.get(i);
            reimbursementDTOs.add(new ReimbursementDTO(r));
        }

        return reimbursementDTOs;

    }

    //Get reimbursement claim by Id


}
