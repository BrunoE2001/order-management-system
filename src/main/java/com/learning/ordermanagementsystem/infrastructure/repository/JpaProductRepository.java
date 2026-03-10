package com.learning.ordermanagementsystem.infrastructure.repository;

import com.learning.ordermanagementsystem.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JpaProductRepository extends JpaRepository<Product, Long>,
        JpaSpecificationExecutor<Product> {
}
