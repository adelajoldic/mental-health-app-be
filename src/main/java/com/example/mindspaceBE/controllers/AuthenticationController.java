package com.example.mindspaceBE.controllers;


import com.example.mindspaceBE.models.AnxietyQuizDTO;
import com.example.mindspaceBE.models.DepressionQuizDTO;
import com.example.mindspaceBE.models.UserDTO;
import com.example.mindspaceBE.models.entities.AnxietyQuizResult;
import com.example.mindspaceBE.models.entities.DepressionQuizResult;
import com.example.mindspaceBE.models.entities.User;
import com.example.mindspaceBE.services.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/profile/{userId}")
    public ResponseEntity<User> getUserProfile(@PathVariable Long userId) {
        User user = authenticationService.getUserProfile(userId);
        return ResponseEntity.ok(user);
    }

    @PostMapping(value = "/anxiety-quiz-result")
    public ResponseEntity<AnxietyQuizResult> saveAnxietyQuizResult(@RequestBody AnxietyQuizDTO anxietyQuizDTO) {
        AnxietyQuizResult savedResult = authenticationService.saveAnxietyQuizResult(anxietyQuizDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedResult);
    }

    @PostMapping(value = "/depression-quiz-result")
    public ResponseEntity<DepressionQuizResult> saveDepressionQuizResult(@RequestBody DepressionQuizDTO depressionQuizDTO) {
        DepressionQuizResult savedResult = authenticationService.saveDepressionQuizResult(depressionQuizDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedResult);
    }
}