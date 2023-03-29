package com.example.livecodeecommerce.controllers;

import com.example.livecodeecommerce.models.Transaction;
import com.example.livecodeecommerce.models.requests.DailyReportRequest;
import com.example.livecodeecommerce.models.requests.MonthlyReportRequest;
import com.example.livecodeecommerce.models.responses.SuccessResponse;
import com.example.livecodeecommerce.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/report")
public class ReportController {
    @Autowired
    private TransactionService transactionService;
    @PostMapping("/dailyReport")
    public ResponseEntity getDailyReportControlller(@RequestBody DailyReportRequest dailyReportRequest){
        List<Transaction> transactionList = transactionService.dailyReport(dailyReportRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<List<Transaction>>("Success Get Daily Report...", transactionList));
    }
    @PostMapping("/monthlyReport")
    public ResponseEntity getMonthlyReportControlller(@RequestBody MonthlyReportRequest monthlyReportRequest){
        List<Transaction> transactionList = transactionService.monthlyReport(monthlyReportRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<List<Transaction>>("Success Get Monthly Report...", transactionList));
    }
}
