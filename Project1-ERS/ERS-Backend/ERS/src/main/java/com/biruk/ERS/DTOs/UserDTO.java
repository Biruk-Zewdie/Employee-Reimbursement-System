package com.biruk.ERS.DTOs;

//DTOs - used to transfer (Auth) data between different layers of our ERS.
//purpose of using DTOs here
// 1. Encapsulation of data - Prevents exposing sensitive Auth info directly to the client.
// 2. Data Transformation - Allows converting database entities into a format suitable for APIs.
// 3. Security - Hides sensitive entity fields from being sent to the client
// 4 .Validation - Enables request validation using annotations like @NotNull, @Size, etc /we can use service layer too

//They are often used to model data that is being sent between client and server

import com.biruk.ERS.models.User;

public class UserDTO {

    //In this case, we want to send User info without including that raw password
    private int userId;
    private String firstName;
    private String lastName;
    private String username

;
    private String email;
    private String role;

    public UserDTO() {

    }

    public UserDTO(int userId, String firstName, String lastName, String username, String email, String role) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public UserDTO (User u){
        this.userId = u.getUserId();
        this.firstName = u.getFirstName();
        this.lastName = u.getLastName();
        this.username = u.getUsername();
        this.email = u.getEmail();
        this.role = u.getRole();
    }

    public int getUserId () {
        return userId;
    }

    public void setUserId (int userId){
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserRegistrationDTO{" +
                "userId='" + userId + '\'' +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username ='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
