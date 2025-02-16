package models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int userId;

    private String firstName;

    private String lastName;

    @Column (nullable = false, unique = true)
    private String userName;

    private String email;

    @Column (nullable = false)
    private String passWord;

    private String role;

    @OneToMany
    private List<ReimbursementClaim> reimbursementClaims;

    public User (){

    }

    public User(int userId, String firstName, String lastName, String userName, String email, String passWord, String role, List<ReimbursementClaim> reimbursementClaims) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.passWord = passWord;
        this.role = role;
        this.reimbursementClaims = reimbursementClaims;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<ReimbursementClaim> getReimbursementClaims() {
        return reimbursementClaims;
    }

    public void setReimbursementClaims(List<ReimbursementClaim> reimbursementClaims) {
        this.reimbursementClaims = reimbursementClaims;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", passWord='" + passWord + '\'' +
                ", role='" + role + '\'' +
                ", reimbursementClaims=" + reimbursementClaims +
                '}';
    }
}
