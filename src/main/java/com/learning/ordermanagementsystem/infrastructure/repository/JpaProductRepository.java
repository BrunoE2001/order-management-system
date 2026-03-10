package com.learning.ordermanagementsystem.infrastructure.repository;

import com.learning.ordermanagementsystem.domain.model.Product;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JpaProductRepository extends JpaRepository<Product, Long>,
        JpaSpecificationExecutor<Product> {

    @Override
    Page<Product> findAll(
            @NonNull Specification<Product> specification,
            @NonNull Pageable pageable
    );
}
