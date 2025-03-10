package com.biruk.ERS.models;

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
    private String username;

    private String email;

    @Column (nullable = false)
    private String password;

    private String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReimbursementClaim> reimbursements;



    public User (){
        this.reimbursements = new ArrayList<>();
    }

    public User(int userId, String firstName, String lastName, String username, String password, String email, String role, List<ReimbursementClaim> reimbursements) {

        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.reimbursements = reimbursements;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassWord(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<ReimbursementClaim> getReimbursements() {
        return reimbursements;
    }

    public void setReimbursements(List<ReimbursementClaim> reimbursements) {
        this.reimbursements = reimbursements;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username ='" + username + '\'' +
                ", email='" + email + '\'' +
                ", passWord='" + password + '\'' +
                ", role='" + role + '\'' +
                ", reimbursementsCount=" + reimbursements.size() +
                '}';
    }
}
