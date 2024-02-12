package com.nada.SpringBootMiniProject.controller;

import com.nada.SpringBootMiniProject.bo.auth.CreateSignupRequest;
import com.nada.SpringBootMiniProject.bo.user.CreateUserRequest;
import com.nada.SpringBootMiniProject.service.auth.AuthService;
import com.nada.SpringBootMiniProject.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/createauser")
    public ResponseEntity<String> createUser(@RequestBody CreateSignupRequest createSignupRequest) {
        try {
            authService.signup(createSignupRequest);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("User created successfully");
    }
    @GetMapping("/userprofile")
    public ResponseEntity<String> viewProfile(@RequestBody CreateSignupRequest createSignupRequest) {
        try {
            authService.signup(createSignupRequest);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("User profile is successfully created ");
    }

}
