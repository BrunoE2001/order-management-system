package com.learning.ordermanagementsystem.api.controller;

import com.learning.ordermanagementsystem.application.dto.CreateOrderRequest;
import com.learning.ordermanagementsystem.application.service.OrderService;
import com.learning.ordermanagementsystem.application.service.ProductService;
import com.learning.ordermanagementsystem.domain.model.Order;
import com.learning.ordermanagementsystem.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<Product>> getOrder(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @PageableDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Page<Product> result = productService.getAllProducts(name, minPrice, maxPrice, pageable);

        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderRequest request) {

        return new ResponseEntity<>(orderService.createOrder(request), HttpStatus.CREATED);

    }
}
