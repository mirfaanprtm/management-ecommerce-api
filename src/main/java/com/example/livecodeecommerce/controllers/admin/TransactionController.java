package com.example.livecodeecommerce.controllers.admin;


import com.example.livecodeecommerce.models.Transaction;
import com.example.livecodeecommerce.models.requests.TransactionRequest;
import com.example.livecodeecommerce.models.responses.SuccessResponse;
import com.example.livecodeecommerce.services.TransactionService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private ModelMapper modelMapper;
    @PostMapping
    public ResponseEntity createTransactionController(@RequestBody TransactionRequest transactionRequest){
        Transaction newTransaction = modelMapper.map(transactionRequest, Transaction.class);
        Transaction transaction = transactionService.createTransactionService(newTransaction);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<Transaction>("Transaction Created...", transaction));
    }

    @GetMapping("/search/{page}/{size}/{sort}")
    public List<Transaction> getAllTransactionController(@PathVariable Integer size, @PathVariable Integer page, @PathVariable("sort") String sort){
        Pageable pageable;
        if(sort.equalsIgnoreCase("desc")){
            pageable = PageRequest.of(page-1, size, Sort.by("id").descending());
        } else {
            pageable = PageRequest.of(page-1, size, Sort.by("id").ascending());
        }
        return transactionService.getAllTransactionService(pageable);
    }
    @GetMapping("/{id}")
    public ResponseEntity getTransactionByIdController(@PathVariable("id") Long id){
        Optional<Transaction> transaction = transactionService.getTransactionByIdService(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<Optional<Transaction>>("Success Get Transaction By ID...", transaction));
    }
    @PutMapping("/{id}")
    public ResponseEntity updateTransactionController(@PathVariable Long id, @Valid @RequestBody TransactionRequest transactionRequest){
        Transaction newTransaction = modelMapper.map(transactionRequest, Transaction.class);
        Transaction transaction = transactionService.updateTransactionService(id, newTransaction);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<Transaction>("Success Update Transaction...", transaction));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteTransactionController(@PathVariable("id") Long id){
        transactionService.deleteTransactionService(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<>("Success Delete Transaction...", "Data Null"));
    }
}
