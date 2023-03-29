package com.example.livecodeecommerce.controllers.user;

import com.example.livecodeecommerce.models.TransactionDetail;
import com.example.livecodeecommerce.models.responses.SuccessResponse;
import com.example.livecodeecommerce.services.TransactionDetailService;
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
@RequestMapping("/user/transactionDetail")
public class UTransactionDetailController {
    @Autowired
    private TransactionDetailService transactionDetailService;
    @Autowired
    private ModelMapper modelMapper;
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

}
