package com.biruk.ERS.DTOs;

import com.biruk.ERS.models.ReimbursementClaim;
import com.biruk.ERS.models.User;

public class ReimbursementDTO {

    private User userId;
    private ReimbursementClaim amount;
    private ReimbursementClaim description;


    public ReimbursementDTO (){

    }

    public ReimbursementDTO (User userId, ReimbursementClaim amount, ReimbursementClaim description){
        this.userId = userId;
        this.amount = amount;
        this.description = description;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public ReimbursementClaim getAmount() {
        return amount;
    }

    public void setAmount(ReimbursementClaim amount) {
        this.amount = amount;
    }

    public ReimbursementClaim getDescription() {
        return description;
    }

    public void setDescription(ReimbursementClaim description) {
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
