package com.biruk.ERS.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
@Entity
@Table (name="reimbursement")
public class ReimbursementClaim {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int requestId;

    @Column(name = "request_date", nullable = false)
    private LocalDateTime requestDate;


    @Column(nullable = false)
    private double amount;

    private String Description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReimbursementStatus reimbursementStatus = ReimbursementStatus.pending;

    @ManyToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;



    public ReimbursementClaim() {
    }

    public ReimbursementClaim(int requestId, LocalDateTime requestDate, double amount, String description, ReimbursementStatus reimbursementStatus, User user) {
        this.requestId = requestId;
        this.requestDate = requestDate;
        this.amount = amount;
        this.Description = description;
        this.reimbursementStatus = reimbursementStatus;
        this.user = user;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public LocalDateTime getRequestDate (){
        return requestDate;
    }

    public void setRequestDate (LocalDateTime requestDate){
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

    public ReimbursementStatus getReimbursementStatus() {
        return reimbursementStatus;
    }

    public void setReimbursementStatus(ReimbursementStatus reimbursementStatus) {
        this.reimbursementStatus = reimbursementStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ReimbursementClaim{" +
                "requestId=" + requestId +
                ", requestDate=" + requestDate +
                ", amount=" + amount +
                ", Description='" + Description + '\'' +
                ", reimbursementStatus=" + reimbursementStatus +
                ", user=" + user +
                '}';
    }

    public enum ReimbursementStatus {
        approved , denied, pending
    }
}
