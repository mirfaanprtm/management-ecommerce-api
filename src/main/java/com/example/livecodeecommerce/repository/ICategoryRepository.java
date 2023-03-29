package com.example.livecodeecommerce.repository;

import com.example.livecodeecommerce.models.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByNameContains(String name, Pageable pageable);
}
