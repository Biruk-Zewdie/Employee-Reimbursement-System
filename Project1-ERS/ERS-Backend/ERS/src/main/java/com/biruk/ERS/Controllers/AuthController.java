package com.biruk.ERS.Controllers;

import com.biruk.ERS.DTOs.LoginDTO;
import com.biruk.ERS.DTOs.UserDTO;
import com.biruk.ERS.Services.AuthService;
import com.biruk.ERS.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin()
public class AuthController {

    private final AuthService authService;

    public AuthController (AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser (@RequestBody User user){

        //send user data to the service(which will send it to the DAO)
        UserDTO returnedUser = authService.registerUser(user);

        return ResponseEntity.ok(returnedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login (@RequestBody LoginDTO loginDTO){

        UserDTO loggedInUser = authService.login(loginDTO);

        //return user info to the clint
        return ResponseEntity.ok(loggedInUser);
    }
}
