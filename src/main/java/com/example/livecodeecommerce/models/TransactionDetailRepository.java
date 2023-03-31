package com.example.livecodeecommerce.models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, Long> {
    List<TransactionDetail> findByTransactionIn(Collection<Transaction> transactions);
}