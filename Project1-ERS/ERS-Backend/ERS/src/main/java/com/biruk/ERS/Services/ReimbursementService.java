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
import java.util.NoSuchElementException;
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

    //Get reimbursement claim by requestId

    public ReimbursementDTO getReimbursementById (int requestId){

        ReimbursementClaim reimbursementClaim = reimbursementDAO.findById(requestId).orElseThrow(() ->
                new NoSuchElementException("Reimbursement not found with Request ID: " + requestId));

        return new ReimbursementDTO(reimbursementClaim);

    }

    //Get all reimbursement claims requested under a specific user(userId)
    public List <ReimbursementDTO> getReimbursementByUserId (int userId){
        List <ReimbursementClaim> claims = reimbursementDAO.getByUser_UserId(userId);

        if (claims.isEmpty()){
            throw new NoSuchElementException("No reimbursement found for user ID: " + userId);
        }

        List <ReimbursementDTO> returnedDtoList = new ArrayList<>();

        for (ReimbursementClaim claim: claims){
            returnedDtoList.add(new ReimbursementDTO(claim));
        }

        return returnedDtoList;
    }



    //Get reimbursement claim by Pending Status



    //Delete reimbursement claim by request ID - only done by manager
    public void deleteReimbursement (int requestId){

        if (!reimbursementDAO.existsById(requestId)){
            throw new NoSuchElementException("User not found with ID: " + requestId);
        }

        reimbursementDAO.deleteById(requestId);

    }

}
