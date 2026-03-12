package com.learning.ordermanagementsystem.application.service;

import com.learning.ordermanagementsystem.application.dto.order.CreateOrderRequest;
import com.learning.ordermanagementsystem.application.dto.order.OrderItemRequest;
import com.learning.ordermanagementsystem.domain.model.Order;
import com.learning.ordermanagementsystem.domain.model.OrderItem;
import com.learning.ordermanagementsystem.domain.model.Product;
import com.learning.ordermanagementsystem.domain.repository.OrderRepository;
import com.learning.ordermanagementsystem.domain.strategy.DiscountStrategy;
import com.learning.ordermanagementsystem.infrastructure.config.DiscountStrategyFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final DiscountStrategyFactory factory;

    public Order createOrder(CreateOrderRequest request) {

        List<OrderItem> items = new ArrayList<>();

        for (OrderItemRequest itemRequest : request.getItems()) {

            Product product = Product.builder()
                    .name(itemRequest.getProductName())
                    .price(BigDecimal.valueOf(itemRequest.getPrice()))
                    .build();

            OrderItem item = OrderItem.builder()
                    .product(product)
                    .quantity(itemRequest.getQuantity())
                    .build();

            items.add(item);
        }

        BigDecimal total = calculateTotal(items);

        DiscountStrategy strategy =
                factory.getStrategy(request.getDiscountType());

        BigDecimal finalTotal = strategy.applyDiscount(total);

        Order order = Order.builder()
                .items(items)
                .total(finalTotal)
                .status("CREATED")
                .build();

        return orderRepository.save(order);
    }

    private BigDecimal calculateTotal(List<OrderItem> items) {

        BigDecimal total = BigDecimal.ZERO;

        for (OrderItem item : items) {

            BigDecimal itemTotal =
                    item.getProduct()
                            .getPrice()
                            .multiply(BigDecimal.valueOf(item.getQuantity()));

            total = total.add(itemTotal);
        }

        return total;
    }
}
