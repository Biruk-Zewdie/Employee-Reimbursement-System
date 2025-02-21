package com.biruk.ERS.Services;

import com.biruk.ERS.DAOs.UserDAO;
import com.biruk.ERS.DTOs.UserDTO;
import com.biruk.ERS.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserService (UserDAO userDAO){
        this.userDAO = userDAO;
    }


    //Get all users from DB

    public List<UserDTO> getAllUsers () {

        //we don't need a validation to fetch all users from the database
        List<User> returnedUsers = userDAO.findAll();

        //convert the users into a List of UserDTOs.
        //we're gonna use our special "user args" constructor from the DTO.
        List<UserDTO> userDTOs = new ArrayList<>();

        //iterate through the users list that we fetched from DB and add to our userDTO type object list.
        //the result would be users with userDTO fields. then return it.
        for (int i = 0; i < returnedUsers.size(); i++){
            User u = returnedUsers.get(i);
            userDTOs.add(new UserDTO(u));
        }

        return userDTOs;

    }

    //get users by userId

    public UserDTO getUserById (int userId){

        User user = userDAO.findById(userId).orElseThrow( () ->
                new NoSuchElementException("User not found with ID : " + userId));

        return new UserDTO(user);

    }

    //Delete user by ID

    public void deleteUserById (int userId){

        if (!userDAO.existsById(userId)){
            throw new NoSuchElementException("User not found with ID: " + userId);
        }
        userDAO.deleteById(userId);
    }


}