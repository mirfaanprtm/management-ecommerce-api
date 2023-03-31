package com.example.livecodeecommerce.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "transactions")
@Getter @Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "transaction_date", nullable = false)
    private Date transactionDate;
    @ManyToOne
    private User user;

    public Transaction(Long id, Date transactionDate, User user) {
        this.id = id;
        this.transactionDate = transactionDate;
        this.user = user;
    }

    public Transaction() {
    }

}
