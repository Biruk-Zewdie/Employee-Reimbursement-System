package com.biruk.ERS.Services;

import com.biruk.ERS.DAOs.UserDAO;
import com.biruk.ERS.DTOs.LoginDTO;
import com.biruk.ERS.DTOs.UserDTO;
import com.biruk.ERS.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserDAO userDAO;

    @Autowired
    private AuthService (UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public UserDTO registerUser (User user){

        User  registeredUser = userDAO.save(user);

        //converting user to userDTO before sending it to the cloud.
        UserDTO newUserDTO = new UserDTO(
                registeredUser.getUserId(),
                registeredUser.getFirstName(),
                registeredUser.getLastName(),
                registeredUser.getUserName(),
                registeredUser.getEmail(),
                registeredUser.getRole()
        );

        return newUserDTO;
    }

    public UserDTO login (LoginDTO loginDTO){

        if (loginDTO.getUsername() == null || loginDTO.getUsername().isBlank()){
            throw new IllegalArgumentException("Username cannot be empty!");
        }
        if (loginDTO.getPassword() == null || loginDTO.getPassword().isBlank()){
            throw new IllegalArgumentException("Password cannot be empty!");
        }

        //try to get User from DAO
        User returnedUser = userDAO.findByUsernameAndPassword(
                loginDTO.getUsername(),
                loginDTO.getPassword()).orElse(null);


        if (returnedUser == null){
            throw new IllegalArgumentException("Invalid username or password!");
        }

        return new UserDTO(returnedUser);

    }


}
