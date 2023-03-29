package com.example.livecodeecommerce.services;

import com.example.livecodeecommerce.models.requests.LoginRequest;
import com.example.livecodeecommerce.models.requests.RegisterRequest;

public interface IAuthService {
    String register(RegisterRequest registerRequest);
    String login(LoginRequest loginRequest);
}
