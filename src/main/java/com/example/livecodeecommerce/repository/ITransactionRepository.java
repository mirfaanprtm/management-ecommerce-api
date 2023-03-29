package com.example.livecodeecommerce.repository;

import com.example.livecodeecommerce.models.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface ITransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByTransactionDateBetween(LocalDate startDate, LocalDate endDate);
    List<Transaction> findByTransactionDate(LocalDate date);

}
