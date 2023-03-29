package com.example.livecodeecommerce.models.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class RegisterRequest {
    @NotBlank(message = "Name is required...")
    @Pattern(regexp = "[^3,100]+", message = "Nama should have 3-100 chararcters")
    private String name;

    @NotBlank(message = "Email is required...")
    @Email(message = "Format email should be username@domain.gTLDs&ccTLDs", regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
    private String email;

    @NotBlank(message = "PHone is required...")
    private String phone;

    @NotBlank(message = "Password is required...")
    private String password;
}
