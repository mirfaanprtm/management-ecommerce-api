package com.example.livecodeecommerce.services;

import com.example.livecodeecommerce.exceptions.NotFoundException;
import com.example.livecodeecommerce.models.Price;
import com.example.livecodeecommerce.models.Transaction;
import com.example.livecodeecommerce.models.TransactionDetail;
import com.example.livecodeecommerce.models.requests.DailyReportRequest;
import com.example.livecodeecommerce.models.requests.MonthlyReportRequest;
import com.example.livecodeecommerce.models.requests.TransactionDetailRequest;
import com.example.livecodeecommerce.models.requests.TransactionRequest;
import com.example.livecodeecommerce.repository.IPriceRepository;
import com.example.livecodeecommerce.repository.ITransactionRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TransactionService {
    @Autowired
    private ITransactionRepository transactionRepository;
    @Autowired
    private IPriceRepository priceRepository;
    @Autowired
    private ModelMapper modelMapper;
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

    public List<Transaction> dailyReport(DailyReportRequest dailyReportRequest){
        try {
            LocalDate date = LocalDate.parse(dailyReportRequest.getDate());
            List<Transaction> transactionList = transactionRepository.findByTransactionDate(date);
            return transactionList;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<Transaction> monthlyReport(MonthlyReportRequest monthlyReportRequest){
        try {
            LocalDate date = LocalDate.parse(monthlyReportRequest.getStartDate());
            LocalDate date1 = LocalDate.parse(monthlyReportRequest.getEndDate());
            List<Transaction> transactionList = transactionRepository.findAllByTransactionDateBetween(date, date1);
            return transactionList;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
