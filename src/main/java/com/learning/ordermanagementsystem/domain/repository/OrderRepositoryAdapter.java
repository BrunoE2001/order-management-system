package com.learning.ordermanagementsystem.domain.repository;

import com.learning.ordermanagementsystem.domain.model.Order;
import com.learning.ordermanagementsystem.infrastructure.repository.JpaOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryAdapter implements OrderRepository {
    private final JpaOrderRepository jpaRepository;


    @Override
    public Order save(Order order) {
        return jpaRepository.save(order);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return jpaRepository.findById(id);
    }
}
