package com.learning.ordermanagementsystem.infrastructure.repository;

import com.learning.ordermanagementsystem.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderRepository
        extends JpaRepository<Order, Long> {
}
