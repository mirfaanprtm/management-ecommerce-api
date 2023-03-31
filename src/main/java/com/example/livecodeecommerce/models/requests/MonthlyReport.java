package com.example.livecodeecommerce.models.requests;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter @Setter
public class MonthlyReport {
    private Date date;
    private Integer qty;
    private Integer price;
    private Integer total;
    private String productName;
}
