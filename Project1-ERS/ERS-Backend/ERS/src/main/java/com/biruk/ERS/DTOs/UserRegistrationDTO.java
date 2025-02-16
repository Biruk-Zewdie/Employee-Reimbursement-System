package com.biruk.ERS.DTOs;

public class UserRegistrationDTO {

    //DTOs - used to transfer (Auth) data between different layers of our ERS.
    //purpose of using DTOs here
    // 1. Encapsulation of data - Prevents exposing sensitive Auth info directly to the client.
    // 2. Data Transformation - Allows converting database entities into a format suitable for APIs.
    // 3. Security - Hides sensitive entity fields from being sent to the client
    // 4 .Validation - Enables request validation using annotations like @NotNull, @Size, etc /we can use service layer too


    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String email;
    private String role;

    public UserRegistrationDTO() {

    }

    public UserRegistrationDTO(String firstName, String lastName, String userName, String password, String email, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.role = role;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
