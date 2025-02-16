package com.biruk.ERS.DTOs;

public class AuthenticatedUserDTO {

    /* The basic function of this class is to determine what the user access/can do based on user role after authentication.
      -If the user is an Employee :-
        1. Create a new Reimbursement
        2. See all reimbursement tickets (only their own)
        3. See only their pending reimbursement tickets

      - If the user is a Finance manager
        1. See all reimbursements
        2. See all pending reimbursements
        3. Resolve a reimbursement (update status from PENDING to APPROVED or DENIED)
        4. See all Users
        5. Delete a User and any related reimbursements
      */

    private int userId;
    private String userName;
    private String role;

    public AuthenticatedUserDTO() {

    }

    public AuthenticatedUserDTO(int userId, String userName, String role) {
        this.userId = userId;
        this.userName = userName;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "AuthenticatedUserDTO{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
