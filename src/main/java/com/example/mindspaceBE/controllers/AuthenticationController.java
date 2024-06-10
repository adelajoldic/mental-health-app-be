package com.example.mindspaceBE.controllers;


import com.example.mindspaceBE.models.UserDTO;
import com.example.mindspaceBE.models.entities.User;
import com.example.mindspaceBE.services.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "")
@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(value = "/register")
    public User registerUser(@RequestBody UserDTO userDTO) {
        return authenticationService.registerNewUser(userDTO);
    }

    @PostMapping(value = "/login")
    public User logInUser(@RequestBody UserDTO userDTO) {
        return authenticationService.logInUser(userDTO);
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        authenticationService.logout(request);
        return ResponseEntity.ok().build();
    }
}