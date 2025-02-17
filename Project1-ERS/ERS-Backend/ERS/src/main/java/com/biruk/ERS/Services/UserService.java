package com.biruk.ERS.Services;

import com.biruk.ERS.DAOs.UserDAO;
import com.biruk.ERS.DTOs.UserRegistrationDTO;
import com.biruk.ERS.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

//In this UserService class we will validate username and password that the user submit
@Service
public class UserService {

    //since the UserService communicate with UserDAO in order to use UserDAO's method, we have injected it a dependency
    private UserDAO userDAO;

    @Autowired
    public UserService (UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User registerUser (UserRegistrationDTO userRegistrationDTO) throws IllegalArgumentException{

        if (userRegistrationDTO.getUserName() == null || userRegistrationDTO.getUserName().isBlank()){
            throw new IllegalArgumentException("Username can't be blank");
        }

        if (userRegistrationDTO.getPassword().length() < 6 || userRegistrationDTO.getPassword().isBlank()){
            throw new IllegalArgumentException("The password must be at least 6 characters long.");
        }

        if (userDAO.findByUserName(userRegistrationDTO.getUserName()).isPresent()){
            throw new DuplicateKeyException("An account with the specified username already exists.");

        }

        //set the default role if it is not given
        String role = userRegistrationDTO.getRole() != null? userRegistrationDTO.getRole() : "employee";

        // if the user submitted registration form successfully, a new user will be created and set the user
        User newUser = new User();
        newUser.setUserName(userRegistrationDTO.getUserName());
        newUser.setPassWord(userRegistrationDTO.getPassword());
        newUser.setEmail(userRegistrationDTO.getEmail());
        newUser.setFirstName(userRegistrationDTO.getFirstName());
        newUser.setLastName(userRegistrationDTO.getLastName());
        newUser.setRole(userRegistrationDTO.getRole());

        return userDAO.save(newUser);

    }






}
