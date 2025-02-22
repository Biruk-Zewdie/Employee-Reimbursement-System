package com.biruk.ERS.Controllers;

import com.biruk.ERS.DTOs.ReimbursementDTO;
import com.biruk.ERS.DTOs.UserDTO;
import com.biruk.ERS.Services.UserService;
import com.biruk.ERS.models.ReimbursementClaim;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@RestController
@RequestMapping ("/users")
@CrossOrigin
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController (UserService userService) {
        this.userService =  userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers () {

        List<UserDTO> users = userService.getAllUsers();

        return ResponseEntity.ok(users);

    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int userId) {

        UserDTO userDTO = userService.getUserById(userId);
        return ResponseEntity.ok(userDTO);

    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUserById (@PathVariable int userId){
        userService.deleteUserById(userId);
        return ResponseEntity.ok("User deleted Successfully!");
    }

}
