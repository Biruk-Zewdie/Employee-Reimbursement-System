package com.biruk.ERS.Controllers;

import com.biruk.ERS.Aspects.ManagerOnly;
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
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:5173" }, allowedHeaders = "*", allowCredentials = "true")
//@CrossOrigin (value = "http://localhost:5173", allowCredentials = "true")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController (UserService userService) {
        this.userService =  userService;
    }

    @GetMapping
    @ManagerOnly
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
    @ManagerOnly
    public ResponseEntity<String> deleteUserById (@PathVariable int userId){
        userService.deleteUserById(userId);
        return ResponseEntity.ok("User deleted Successfully!");
    }

}
