package com.example.livecodeecommerce.repository;

import com.example.livecodeecommerce.models.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContains(String name, Pageable pageable);

}
