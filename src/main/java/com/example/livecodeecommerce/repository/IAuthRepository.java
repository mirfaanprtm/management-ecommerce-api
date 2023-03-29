package com.example.livecodeecommerce.repository;

import com.example.livecodeecommerce.models.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthRepository extends JpaRepository<Auth, String> {

}
