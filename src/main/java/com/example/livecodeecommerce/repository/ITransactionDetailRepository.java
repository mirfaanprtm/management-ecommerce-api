package com.example.livecodeecommerce.repository;

import com.example.livecodeecommerce.models.TransactionDetail;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITransactionDetailRepository extends JpaRepository<TransactionDetail, Long> {

}
