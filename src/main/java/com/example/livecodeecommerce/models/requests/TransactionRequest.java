package com.example.livecodeecommerce.models.requests;

import com.example.livecodeecommerce.models.User;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class TransactionRequest {
    private LocalDate transactionDate;
    private User user;
}
