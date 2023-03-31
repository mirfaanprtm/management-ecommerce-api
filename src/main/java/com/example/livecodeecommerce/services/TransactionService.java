package com.example.livecodeecommerce.services;

import com.example.livecodeecommerce.models.*;
import com.example.livecodeecommerce.models.requests.DailyReport;
import com.example.livecodeecommerce.models.requests.MonthlyReport;
import com.example.livecodeecommerce.repository.IPriceRepository;
import com.example.livecodeecommerce.repository.ITransactionRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class TransactionService {
    @Autowired
    private ITransactionRepository transactionRepository;
    @Autowired
    private TransactionDetailRepository transactionDetailRepository;

    public Transaction createTransactionService(Transaction transaction){
        try {
            return transactionRepository.save(transaction);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<Transaction> getAllTransactionService(Pageable pageable){
        try {
            Page<Transaction> transactions = transactionRepository.findAll(pageable);
            if(transactions.isEmpty()){
                throw new RuntimeException("No Transaction Found");
            }
            return transactionRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public Optional<Transaction> getTransactionByIdService(Long id){
        try {
            Optional<Transaction> transaction = transactionRepository.findById(id);
            if(transaction.isEmpty()){
                throw new RuntimeException("Transaction ID Not Found");
            }
            return transaction;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public Transaction updateTransactionService(Long id, Transaction transaction){
        try {
            Optional<Transaction> transaction1 = transactionRepository.findById(id);
            if(transaction1.isEmpty())
                throw new RuntimeException("Transaction ID Not Found");
            transaction1.get().setTransactionDate(transaction.getTransactionDate());
            transaction1.get().setUser(transaction.getUser());
            return transactionRepository.save(transaction1.get());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public void deleteTransactionService(Long id){
        try {
            Optional<Transaction> transaction = transactionRepository.findById(id);
            if(transaction.isEmpty())
                throw new RuntimeException("Transaction ID Not Found");
            transactionRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<DailyReport> dailyReport(String day){
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(day);
            List<Transaction> getTransaction = transactionRepository.findByTransactionDate(date);
            List<TransactionDetail> findTransaction = transactionDetailRepository.findByTransactionIn(getTransaction);

            Map<Long, DailyReport> dailyReport1 = new HashMap<>();
            for (TransactionDetail transactionDetail : findTransaction) {
                Price price = transactionDetail.getPrice();
                DailyReport dailyReport = dailyReport1.getOrDefault(price.getProduct().getId(), new DailyReport());
                dailyReport.setQty(transactionDetail.getQty());
                dailyReport.setDate(day);
                dailyReport.setProductName(price.getProduct().getName());
                dailyReport.setPrice(price.getPrice());
                dailyReport.setTotal((transactionDetail.getQty()*price.getPrice()));
                dailyReport1.put(price.getId(), dailyReport);
            }
            List<DailyReport> reports = new ArrayList<>(dailyReport1.values());
            return reports;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<MonthlyReport> monthlyReport(String startDate, String endDate){
        try {
            Date start = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
            Date end = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
            List<Transaction> getTransaction = transactionRepository.findAllByTransactionDateBetween(start, end);
            List<TransactionDetail> findTransaction = transactionDetailRepository.findByTransactionIn(getTransaction);

            Map<Integer, MonthlyReport> dailyReport = new HashMap<>();
            for (TransactionDetail transactionDetail : findTransaction) {
                Price price = transactionDetail.getPrice();
                MonthlyReport monthlyReport = dailyReport.getOrDefault(price.getProduct().getId(), new MonthlyReport());
                monthlyReport.setQty(transactionDetail.getQty());
                monthlyReport.setDate(transactionDetail.getTransaction().getTransactionDate());
                monthlyReport.setProductName(price.getProduct().getName());
                monthlyReport.setPrice(price.getPrice());
                monthlyReport.setTotal((transactionDetail.getQty())*(price.getPrice()));
                dailyReport.put(Math.toIntExact(price.getProduct().getId()), monthlyReport);
            }
            List<MonthlyReport> reports = new ArrayList<>(dailyReport.values());
            return reports;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
