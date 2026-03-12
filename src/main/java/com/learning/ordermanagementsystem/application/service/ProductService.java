package com.learning.ordermanagementsystem.application.service;

import com.learning.ordermanagementsystem.application.dto.product.CreateProductRequest;
import com.learning.ordermanagementsystem.application.mapper.product.ProductMapper;
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
    private final ProductMapper productMapper;

    public Page<Product> getAllProducts(
            String name,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            Pageable pageable) {

        return productRepository.findAll(name, minPrice, maxPrice, pageable);

    }

    public Product getByIdProduct(Long id) {
        return productRepository.getByIdProduct(id);
    }

    public Long saveProduct(CreateProductRequest request) {
        Product product = productMapper.toEntity(request);

        Product saved = productRepository.saveProduct(product);

        return saved.getId();
    }
}
