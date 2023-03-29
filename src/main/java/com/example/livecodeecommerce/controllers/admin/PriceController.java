package com.example.livecodeecommerce.controllers.admin;

import com.example.livecodeecommerce.models.Price;
import com.example.livecodeecommerce.models.Product;
import com.example.livecodeecommerce.models.requests.PriceRequest;
import com.example.livecodeecommerce.models.requests.ProductRequest;
import com.example.livecodeecommerce.models.requests.SearchData;
import com.example.livecodeecommerce.models.responses.SuccessResponse;
import com.example.livecodeecommerce.services.PriceService;
import com.example.livecodeecommerce.services.ProductService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/price")
public class PriceController {
    @Autowired
    private PriceService priceService;
    @Autowired
    private ModelMapper modelMapper;
    @PostMapping
    public ResponseEntity createPriceController(@RequestBody PriceRequest priceRequest){
        Price newPrice = modelMapper.map(priceRequest, Price.class);
        Price price = priceService.createPriceService(newPrice);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<Price>("Price Created...", price));
    }
    @GetMapping("/search/{page}/{size}/{sort}")
    public Page<Price> getAllPriceController(@PathVariable Integer size, @PathVariable Integer page, @PathVariable("sort") String sort){
        Pageable pageable;
        if(sort.equalsIgnoreCase("desc")){
            pageable = PageRequest.of(page-1, size, Sort.by("id").descending());
        } else {
            pageable = PageRequest.of(page-1, size, Sort.by("id").ascending());
        }
        return priceService.getAllPriceService(pageable);
    }
    @GetMapping("/{id}")
    public ResponseEntity getPriceByIdController(@PathVariable("id") Long id){
        Optional<Price> price = priceService.getPriceByIdService(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<Optional<Price>>("Success Get Price By ID...", price));
    }
    @PutMapping("/{id}")
    public ResponseEntity updatePriceController(@PathVariable Long id, @Valid @RequestBody PriceRequest priceRequest){
        Price newPrice = modelMapper.map(priceRequest, Price.class);
        Price price = priceService.updatePriceService(id, newPrice);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<Price>("Success Update Price...", price));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deletePriceController(@PathVariable("id") Long id){
        priceService.deletePriceService(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<>("Success Delete Price...", "Data Null"));
    }

}
