package com.biruk.ERS.Controllers;

import com.biruk.ERS.DTOs.LoginDTO;
import com.biruk.ERS.DTOs.UserDTO;
import com.biruk.ERS.Services.AuthService;
import com.biruk.ERS.models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    public ResponseEntity<UserDTO> login (@RequestBody LoginDTO loginDTO, HttpSession session){

        UserDTO loggedInUser = authService.login(loginDTO);

        session.setAttribute("userId", loggedInUser.getUserId());
        session.setAttribute("username", loggedInUser.getUsername());
        session.setAttribute("role", loggedInUser.getRole());

        System.out.println("User " + session.getAttribute("username") + " has logged in!");

        //return user info to the clint
        return ResponseEntity.ok(loggedInUser);
    }

    @PostMapping ("/logout")
    public ResponseEntity<String> logout (HttpServletRequest request){

        HttpSession session = request.getSession(false);

        if (session != null){
            session.invalidate();
        }

        return ResponseEntity.ok("Logged out successfully!");
    }
}
