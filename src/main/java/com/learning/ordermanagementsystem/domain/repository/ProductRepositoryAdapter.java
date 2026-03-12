package com.learning.ordermanagementsystem.domain.repository;

import com.learning.ordermanagementsystem.domain.model.Product;
import com.learning.ordermanagementsystem.infrastructure.repository.JpaProductRepository;
import com.learning.ordermanagementsystem.infrastructure.specification.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepository {
    private final JpaProductRepository jpaRepository;

    @Override
    public Page<Product> findAll(String name, BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable) {
        Specification<Product> spec = ProductSpecification
                .hasName(name)
                .and(ProductSpecification.minPrice(minPrice))
                .and(ProductSpecification.maxPrice(maxPrice));

        return jpaRepository.findAll(spec, pageable);
    }

    @Override
    public Product getByIdProduct(Long id) {
        return jpaRepository.findById(id).orElse(null);
    }

    @Override
    public Product saveProduct(Product product) {
        return jpaRepository.save(product);
    }
}
