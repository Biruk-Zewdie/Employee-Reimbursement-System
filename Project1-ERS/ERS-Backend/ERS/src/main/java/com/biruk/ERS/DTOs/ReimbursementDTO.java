package com.biruk.ERS.DTOs;

import com.biruk.ERS.models.ReimbursementClaim;
import com.biruk.ERS.models.User;

import java.time.LocalDateTime;

public class ReimbursementDTO {

    private int requestId;
    private LocalDateTime requestDate;
    private double amount;
    private String description;
    private ReimbursementClaim.ReimbursementStatus reimbursementStatus;
    private int userId;


    public ReimbursementDTO (){
        this.reimbursementStatus = ReimbursementClaim.ReimbursementStatus.pending;

    }

    public ReimbursementDTO(int requestId, LocalDateTime requestDate, double amount, String description,
                            ReimbursementClaim.ReimbursementStatus reimbursementStatus, int userId) {
        this.requestId = requestId;
        this.requestDate = requestDate;
        this.amount = amount;
        this.description = description;
        this.reimbursementStatus = reimbursementStatus;
        this.userId = userId;
    }

    //see this in use in get all reimbursements in ReimbursementService
    //way cleaner way to format a Reimbursements into a DTO

    public ReimbursementDTO (ReimbursementClaim r) {
        this.requestId = r.getRequestId();
        this.requestDate = r.getRequestDate();
        this.amount = r.getAmount();
        this.description = r.getDescription();
        this.reimbursementStatus = r.getReimbursementStatus();
        this.userId = r.getUser().getUserId();
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }


    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ReimbursementClaim.ReimbursementStatus getReimbursementStatus() {
        return reimbursementStatus;
    }

    public void setReimbursementStatus(ReimbursementClaim.ReimbursementStatus reimbursementStatus) {
        this.reimbursementStatus = reimbursementStatus;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ReimbursementDTO{" +
                "requestId=" + requestId +
                ", requestDate=" + requestDate +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", reimbursementStatus=" + reimbursementStatus +
                ", userId=" + userId +
                '}';
    }
}
