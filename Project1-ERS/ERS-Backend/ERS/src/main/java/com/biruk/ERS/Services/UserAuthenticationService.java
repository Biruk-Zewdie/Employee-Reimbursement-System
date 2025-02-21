package com.biruk.ERS.Services;

import com.biruk.ERS.DAOs.UserDAO;
import com.biruk.ERS.DTOs.UserDTO;
import com.biruk.ERS.models.User;
import org.springframework.beans.factory.annotation.Autowired;

public class UserAuthenticationService {

    private final UserDAO userDAO;

    @Autowired
    public UserAuthenticationService (UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public UserDTO registerUser (User user) {


        //TODO : input validation
        // - check whether the user has already account or not
        // - check the username is valid username (not empty field)
        // - check the password is not less than 6 characters

        User newUser = userDAO.save(user);


        // we should convert the User to a userDTO before we send it to the client
        UserDTO newUserDTO = new UserDTO(
                newUser.getUserId(),
                newUser.getFirstName(),
                newUser.getLastName(),
                newUser.getUserName(),
                newUser.getEmail(),
                newUser.getRole()
        );

        return newUserDTO;

    }
}



