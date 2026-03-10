package com.learning.ordermanagementsystem.domain.repository;

import com.learning.ordermanagementsystem.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface ProductRepository {
    Page<Product> findAll(String name, BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);
}
