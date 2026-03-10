package com.learning.ordermanagementsystem.application.service;

import com.learning.ordermanagementsystem.domain.model.Product;
import com.learning.ordermanagementsystem.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Page<Product> getAllProducts(
            String name,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            Pageable pageable) {

        return productRepository.findAll(name, minPrice, maxPrice, pageable);

    }
}
