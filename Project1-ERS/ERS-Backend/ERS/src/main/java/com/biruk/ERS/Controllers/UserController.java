package com.biruk.ERS.Controllers;

import com.biruk.ERS.DTOs.UserDTO;
import com.biruk.ERS.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        return ResponseEntity.ok(userService.getAllUsers());

    }
//
//    @GetMapping("/{Id}")
//    public ResponseEntity<UserDTO> getUserById (@PathVariable int id) {
//
//
//
//    }



}
