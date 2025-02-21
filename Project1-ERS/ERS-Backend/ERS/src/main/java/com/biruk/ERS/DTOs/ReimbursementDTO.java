package com.biruk.ERS.DTOs;

import com.biruk.ERS.models.ReimbursementClaim;
import com.biruk.ERS.models.User;

public class ReimbursementDTO {

    private int userId;
    private double amount;
    private String description;


    public ReimbursementDTO (){

    }

    public ReimbursementDTO (int userId, double amount, String description){
        this.userId = userId;
        this.amount = amount;
        this.description = description;

    }

    //see this in use in get all reimbursements in ReimbursementService
    //way cleaner way to format a Reimbursements into a DTO

    public ReimbursementDTO (ReimbursementClaim r) {
        this.userId = r.getRequestId();
        this.amount = r.getAmount();
        this.description = r.getDescription();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "ReimbursementDTO{" +
                "userId=" + userId +
                ", amount=" + amount +
                ", description=" + description +
                '}';
    }
}
