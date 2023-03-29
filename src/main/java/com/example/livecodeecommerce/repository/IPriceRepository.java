package com.example.livecodeecommerce.repository;

import com.example.livecodeecommerce.models.Price;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPriceRepository extends JpaRepository<Price, Long> {

}
