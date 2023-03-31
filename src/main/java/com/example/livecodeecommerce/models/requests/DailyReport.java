package com.example.livecodeecommerce.models.requests;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class DailyReport {
    private String date;
    private Integer qty;
    private Integer price;
    private Integer total;
    private String productName;
//    public DailyReport() {
//        this.total = Integer;
//    }
}
