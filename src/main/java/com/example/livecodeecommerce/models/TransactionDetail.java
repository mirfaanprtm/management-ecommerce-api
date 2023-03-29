package com.example.livecodeecommerce.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "transaction_detail")
@Getter @Setter
public class TransactionDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Transaction transaction;
    @ManyToOne
    private Price price;

    @Column(nullable = false)
    private Integer qty;

    public TransactionDetail(Long id, Transaction transaction, Price price, Integer qty) {
        this.id = id;
        this.transaction = transaction;
        this.price = price;
        this.qty = qty;
    }

    public TransactionDetail() {
    }
}
