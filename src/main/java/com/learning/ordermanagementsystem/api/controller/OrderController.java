package com.learning.ordermanagementsystem.api.controller;

import com.learning.ordermanagementsystem.application.dto.order.CreateOrderRequest;
import com.learning.ordermanagementsystem.application.service.OrderService;
import com.learning.ordermanagementsystem.domain.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderRequest request) {

        return new ResponseEntity<>(orderService.createOrder(request), HttpStatus.CREATED);

    }
}
