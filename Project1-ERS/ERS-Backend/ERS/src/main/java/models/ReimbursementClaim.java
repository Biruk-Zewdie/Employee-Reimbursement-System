package models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Entity
@Table(name="reimbursement")

public class ReimbursementClaim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reimbursementId;

    @Column (nullable = false)
    private Date requestDate;

    @Column (nullable = false)
    private double amount;

    private String description;

    private ReimbursementStatus reimbursementStatus;

    @ManyToOne
    private User user;


    public enum ReimbursementStatus {
        approved , denied , pending
    }


    public ReimbursementClaim() {

    }

    public ReimbursementClaim(int reimbursementId, Date requestDate, double amount, String description, ReimbursementStatus reimbursementStatus, User user) {
        this.reimbursementId = reimbursementId;
        this.requestDate = requestDate;
        this.amount = amount;
        this.description = description;
        this.reimbursementStatus = reimbursementStatus;
        this.user = user;
    }

    public int getReimbursementId() {
        return reimbursementId;
    }

    public void setReimbursementId(int reimbursementId) {
        this.reimbursementId = reimbursementId;
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
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
                "reimbursementId=" + reimbursementId +
                ", requestDate=" + requestDate +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", reimbursementStatus=" + reimbursementStatus +
                ", user=" + user +
                '}';
    }
}
