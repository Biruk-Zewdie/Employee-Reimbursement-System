package com.biruk.ERS.Services;

import com.biruk.ERS.DAOs.UserDAO;
import com.biruk.ERS.DTOs.LoginDTO;
import com.biruk.ERS.DTOs.UserDTO;
import com.biruk.ERS.Exceptions.UsernameAlreadyExistsException;
import com.biruk.ERS.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserDAO userDAO;

    @Autowired
    private AuthService (UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public UserDTO registerUser (User user){

        System.out.println(user);

        if (userDAO.findByUsername(user.getUsername()).isPresent()){
            throw new UsernameAlreadyExistsException("Username '" + user.getUsername() + "' is already taken.");
        }

        try{
            User  registeredUser = userDAO.save(user);

            //converting user to userDTO before sending it to the cloud.
            return new UserDTO(
                    registeredUser.getUserId(),
                    registeredUser.getFirstName(),
                    registeredUser.getLastName(),
                    registeredUser.getUsername(),
                    registeredUser.getEmail(),
                    registeredUser.getRole()
            );
        }catch(DataIntegrityViolationException e){
            throw new UsernameAlreadyExistsException("Username '" + user.getUsername() + "' is already taken.");

        }
    }

    public UserDTO login (LoginDTO loginDTO){

        System.out.println(loginDTO);

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

        System.out.println(returnedUser);

        if (returnedUser == null){
            throw new IllegalArgumentException("Invalid username or password!");
        }

        return new UserDTO(returnedUser);

    }


}
