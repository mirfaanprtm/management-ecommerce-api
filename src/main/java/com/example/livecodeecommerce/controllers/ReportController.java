package com.example.livecodeecommerce.controllers;

import com.example.livecodeecommerce.models.Transaction;
import com.example.livecodeecommerce.models.requests.DailyReport;
import com.example.livecodeecommerce.models.requests.MonthlyReport;
import com.example.livecodeecommerce.models.responses.SuccessResponse;
import com.example.livecodeecommerce.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/report")
public class ReportController {
    @Autowired
    private TransactionService transactionService;
    @PostMapping("/dailyReport")
    public ResponseEntity getDailyReportControlller(@RequestParam String date){
        List<DailyReport> dailyReport1 = transactionService.dailyReport(date);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<List<DailyReport>>("Success Get Daily Report...", dailyReport1));
    }
    @PostMapping("/monthlyReport")
    public ResponseEntity getMonthlyReportControlller(@RequestParam String startDate, String endDate){
        List<MonthlyReport> monthlyReport1 = transactionService.monthlyReport(startDate, endDate);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<List<MonthlyReport>>("Success Get Monthly Report...", monthlyReport1));
    }
}
