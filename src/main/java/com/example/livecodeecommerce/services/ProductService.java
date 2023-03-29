package com.example.livecodeecommerce.services;

import com.example.livecodeecommerce.models.Category;
import com.example.livecodeecommerce.models.Product;
import com.example.livecodeecommerce.repository.IProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@Transactional
public class ProductService {
    @Autowired
    IProductRepository productRepository;
    public Product createProductService(Product product){
        try {
            return productRepository.save(product);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<Product> getAllProductService(){
        try {
            List<Product> products = productRepository.findAll();
            if(products.isEmpty()){
                throw new RuntimeException("No Product Found");
            }
            return productRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public Optional<Product> getProductByIdService(Long id){
        try {
            Optional<Product> product = productRepository.findById(id);
            if(product.isEmpty()){
                throw new RuntimeException("Product ID Not Found");
            }
            return product;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public Product updateProductService(Long id, Product product){
        try {
            Optional<Product> product1 = productRepository.findById(id);
            if(product1.isEmpty())
                throw new RuntimeException("Product ID Not Found");
            product1.get().setName(product.getName());
            product1.get().setDescription(product.getDescription());
            product1.get().setCategory(product.getCategory());
            return productRepository.save(product1.get());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public void deleteProductService(Long id){
        try {
            Optional<Product> product = productRepository.findById(id);
            if(product.isEmpty())
                throw new RuntimeException("Product ID Not Found");
            productRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Product> getProductByNameService(String name, Pageable pageable){
        try {
            List<Product> products = productRepository.findByNameContains(name, pageable);
            if(products.isEmpty())
                throw new RuntimeException("Category Name Not Found");
            return products;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
