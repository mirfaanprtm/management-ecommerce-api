package com.example.livecodeecommerce.services;

import com.example.livecodeecommerce.exceptions.NotFoundException;
import com.example.livecodeecommerce.models.Price;
import com.example.livecodeecommerce.models.Product;
import com.example.livecodeecommerce.repository.IPriceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.PrinterURI;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PriceService {
    @Autowired
    private IPriceRepository priceRepository;
    public Price createPriceService(Price price){
        try {
            Optional<Price> price1 = priceRepository.findById(price.getId());
            if(price1.isPresent())
                throw new RuntimeException("Price already exists...");
            return priceRepository.save(price);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public Page<Price> getAllPriceService(Pageable pageable){
        try {
            Page<Price> prices = priceRepository.findAll(pageable);
            if(prices.isEmpty()){
                throw new NotFoundException("No Price Found");
            }
            return prices;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public Optional<Price> getPriceByIdService(Long id){
        try {
            Optional<Price> price = priceRepository.findById(id);
            if(price.isEmpty()){
                throw new RuntimeException("Price ID Not Found");
            }
            return price;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public Price updatePriceService(Long id, Price price){
        try {
            Optional<Price> price1 = priceRepository.findById(id);
            if(price1.isEmpty())
                throw new RuntimeException("Price ID Not Found");
            price1.get().setPrice(price.getPrice());
            price1.get().setStock(price.getStock());
            price1.get().setProduct(price.getProduct());
            return priceRepository.save(price1.get());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public void deletePriceService(Long id){
        try {
            Optional<Price> price = priceRepository.findById(id);
            if(price.isEmpty())
                throw new RuntimeException("Price ID Not Found");
            priceRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
