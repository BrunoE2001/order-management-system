package com.learning.ordermanagementsystem.domain.repository;

import com.learning.ordermanagementsystem.domain.model.Order;

import java.util.Optional;

public interface OrderRepository {
    Order save(Order order);

    Optional<Order> findById(Long id);
}
