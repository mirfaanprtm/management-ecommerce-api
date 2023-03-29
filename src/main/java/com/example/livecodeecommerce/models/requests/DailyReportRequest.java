package com.example.livecodeecommerce.models.requests;

import com.example.livecodeecommerce.models.Transaction;
import com.example.livecodeecommerce.models.TransactionDetail;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter @Setter
public class DailyReportRequest {
    private String date;
    private Integer jumlahTransaksi;
    private Integer totalBelanjaHarian;}
