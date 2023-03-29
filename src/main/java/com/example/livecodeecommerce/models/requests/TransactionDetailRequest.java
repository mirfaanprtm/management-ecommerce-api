package com.example.livecodeecommerce.models.requests;

import com.example.livecodeecommerce.models.Price;
import com.example.livecodeecommerce.models.Transaction;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TransactionDetailRequest {
    private Transaction transaction;
    private Price price;
    private Integer qty;
}
