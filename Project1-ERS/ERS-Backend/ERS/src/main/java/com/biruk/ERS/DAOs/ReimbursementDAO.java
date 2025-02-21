package com.biruk.ERS.DAOs;

import com.biruk.ERS.models.ReimbursementClaim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ReimbursementDAO extends JpaRepository <ReimbursementClaim, Integer>{

    public List<ReimbursementClaim> getByUser_UserId (int userId);

    //Fetch reimbursements with "Pending..." status.
    public List<ReimbursementClaim> findByReimbursementStatus(ReimbursementClaim.ReimbursementStatus status);
}
