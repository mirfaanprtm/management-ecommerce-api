package com.example.livecodeecommerce.models.requests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserRequest {
    private String name;
    private String email;
    private String password;
    private String phone;
}
