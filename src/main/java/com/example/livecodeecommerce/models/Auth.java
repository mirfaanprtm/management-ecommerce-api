package com.example.livecodeecommerce.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "auth")
@Getter @Setter
public class Auth {
    @Id
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(name = "is_active", columnDefinition = "boolean default false")
    private boolean isActive;
}
