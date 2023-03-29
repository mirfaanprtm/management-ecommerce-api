package com.example.livecodeecommerce.models.requests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MonthlyReportRequest {
    private String startDate;
    private String endDate;
}
