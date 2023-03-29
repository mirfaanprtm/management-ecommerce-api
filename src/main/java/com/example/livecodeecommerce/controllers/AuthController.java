package com.example.livecodeecommerce.controllers;

import com.example.livecodeecommerce.models.requests.LoginRequest;
import com.example.livecodeecommerce.models.requests.RegisterRequest;
import com.example.livecodeecommerce.models.responses.SuccessResponse;
import com.example.livecodeecommerce.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequest registerRequest){
        String token = authService.register(registerRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<>("Registration success...", token));
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest){
        String token = authService.login(loginRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<>("Login success...", token));
    }
}
