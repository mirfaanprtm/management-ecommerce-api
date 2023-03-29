package com.example.livecodeecommerce.models.requests;

import com.example.livecodeecommerce.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductRequest {
    private String name;
    private String description;
    private Category category;
}
