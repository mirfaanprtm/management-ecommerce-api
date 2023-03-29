package com.example.livecodeecommerce.controllers.admin;

import com.example.livecodeecommerce.models.TransactionDetail;
import com.example.livecodeecommerce.models.requests.TransactionDetailRequest;
import com.example.livecodeecommerce.models.responses.SuccessResponse;
import com.example.livecodeecommerce.services.TransactionDetailService;
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
@RequestMapping("/admin/transactionDetail")
public class TransactionDetailController {
    @Autowired
    private TransactionDetailService transactionDetailService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity createTransactionDetailController(@RequestBody TransactionDetailRequest transactionDetailRequest){
        TransactionDetail transactionDetail = transactionDetailService.createTransactionDetailService(transactionDetailRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<TransactionDetail>("Transaction Detail Created...", transactionDetail));
    }
    @GetMapping("/search/{page}/{size}/{sort}")
    public List<TransactionDetail> getAllTransactionDetailController(@PathVariable Integer size, @PathVariable Integer page, @PathVariable("sort") String sort){
        Pageable pageable;
        if(sort.equalsIgnoreCase("desc")){
            pageable = PageRequest.of(page-1, size, Sort.by("id").descending());
        } else {
            pageable = PageRequest.of(page-1, size, Sort.by("id").ascending());
        }
        return transactionDetailService.getAllTransactionDetailService(pageable);
    }
    @GetMapping("/{id}")
    public ResponseEntity getTransactionDetailByIdController(@PathVariable("id") Long id){
        Optional<TransactionDetail> transactionDetail = transactionDetailService.getTransactionDetailByIdService(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<Optional<TransactionDetail>>("Success Get Transaction Detail By ID...", transactionDetail));
    }
    @PutMapping("/{id}")
    public ResponseEntity updateTransactionDetailController(@PathVariable Long id, @Valid @RequestBody TransactionDetailRequest transactionDetailRequest){
        TransactionDetail newTransactionDetail = modelMapper.map(transactionDetailRequest, TransactionDetail.class);
        TransactionDetail transactionDetail = transactionDetailService.updateTransactionDetailService(id, newTransactionDetail);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<TransactionDetail>("Success Update Transaction Detail...", transactionDetail));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteTransactionDetailController(@PathVariable("id") Long id){
        transactionDetailService.deleteTransactionDetailService(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<>("Success Delete Transaction Detail...", "Data Null"));
    }
}
