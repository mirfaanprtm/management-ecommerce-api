package com.example.livecodeecommerce.models.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class CommonResponse {
    private String status;
    private String message;
    private String code;

}
