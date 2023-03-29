package com.example.livecodeecommerce.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "prices")
@Getter @Setter
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Integer price;
    @Column(nullable = false)
    private Integer stock;
    @ManyToOne
    private Product product;

    public Price(Long id, Integer price, Integer stock, Product product) {
        this.id = id;
        this.price = price;
        this.stock = stock;
        this.product = product;
    }

    public Price() {
    }
}
