package com.example.livecodeecommerce.services;

import com.example.livecodeecommerce.models.Price;
import com.example.livecodeecommerce.models.Transaction;
import com.example.livecodeecommerce.models.TransactionDetail;
import com.example.livecodeecommerce.models.requests.TransactionDetailRequest;
import com.example.livecodeecommerce.repository.IPriceRepository;
import com.example.livecodeecommerce.repository.ITransactionDetailRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@Transactional
public class TransactionDetailService {
    @Autowired
    ITransactionDetailRepository transactionDetailRepository;
    @Autowired
    IPriceRepository priceRepository;
    @Autowired
    private ModelMapper modelMapper;

    public TransactionDetail createTransactionDetailService(TransactionDetailRequest transactionDetailRequest){
        try {
            Optional<Price> purchase = priceRepository.findById(transactionDetailRequest.getPrice().getId());
            TransactionDetail transactionDetail = modelMapper.map(transactionDetailRequest, TransactionDetail.class);
            transactionDetail.setPrice(purchase.get());
            purchase.get().setStock(purchase.get().getStock() - transactionDetail.getQty());
            transactionDetail = transactionDetailRepository.save(transactionDetail);
            priceRepository.save(purchase.get());
            return transactionDetail;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<TransactionDetail> getAllTransactionDetailService(Pageable pageable){
        try {
            Page<TransactionDetail> transactionDetails = transactionDetailRepository.findAll(pageable);
            if(transactionDetails.isEmpty()){
                throw new RuntimeException("No Transaction Detail Found");
            }
            return transactionDetailRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public Optional<TransactionDetail> getTransactionDetailByIdService(Long id){
        try {
            Optional<TransactionDetail> transactionDetail = transactionDetailRepository.findById(id);
            if(transactionDetail.isEmpty()){
                throw new RuntimeException("Transaction Detail ID Not Found");
            }
            return transactionDetail;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public TransactionDetail updateTransactionDetailService(Long id, TransactionDetail transactionDetail){
        try {
            Optional<TransactionDetail> transactionDetail1 = transactionDetailRepository.findById(id);
            if(transactionDetail1.isEmpty())
                throw new RuntimeException("Transaction Detail ID Not Found");
            transactionDetail1.get().setTransaction(transactionDetail.getTransaction());
            transactionDetail1.get().setPrice(transactionDetail.getPrice());
            transactionDetail1.get().setQty(transactionDetail.getQty());
            return transactionDetailRepository.save(transactionDetail1.get());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public void deleteTransactionDetailService(Long id){
        try {
            Optional<TransactionDetail> transactionDetail = transactionDetailRepository.findById(id);
            if(transactionDetail.isEmpty())
                throw new RuntimeException("Transaction Detail ID Not Found");
            transactionDetailRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
