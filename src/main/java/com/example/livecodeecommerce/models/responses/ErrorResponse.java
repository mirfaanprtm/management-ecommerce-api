package com.example.livecodeecommerce.models.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ErrorResponse extends CommonResponse{
    public ErrorResponse(String code, String message) {
        super.setStatus("FAILED");
        super.setCode(code);
        super.setMessage(message);
    }
}
