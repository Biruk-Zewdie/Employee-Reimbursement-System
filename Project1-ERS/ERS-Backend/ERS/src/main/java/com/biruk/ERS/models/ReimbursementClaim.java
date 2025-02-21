package com.biruk.ERS.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Entity
@Table (name="reimbursement")
public class ReimbursementClaim {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int requestId;

    @Column(nullable = false)
    private Date requestDate;

    @Column(nullable = false)
    private double amount;

    private String Description;

    private ReimbursementStatus reimbursementStatus;

    @ManyToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;



    public ReimbursementClaim() {
    }

    public ReimbursementClaim(int requestId, Date requestDate, double amount, String description, User user, ReimbursementStatus reimbursementStatus) {
        this.requestId = requestId;
        this.requestDate = requestDate;
        this.amount = amount;
        this.Description = description;
        this.user = user;
        this.reimbursementStatus = reimbursementStatus;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ReimbursementStatus getReimbursementStatus() {
        return reimbursementStatus;
    }

    public void setReimbursementStatus(ReimbursementStatus reimbursementStatus) {
        this.reimbursementStatus = reimbursementStatus;
    }

    @Override
    public String toString() {
        return "ReimbursementClaim{" +
                "requestId=" + requestId +
                ", requestDate=" + requestDate +
                ", amount=" + amount +
                ", Description='" + Description + '\'' +
                ", user=" + user +
                ", reimbursementStatus=" + reimbursementStatus +
                '}';
    }

    public enum ReimbursementStatus {
        approved , denied, pending
    }
}
