package com.example.livecodeecommerce.models.requests;

import com.example.livecodeecommerce.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PriceRequest {
    private Integer price;
    private Integer stock;
    private Product product;
}
