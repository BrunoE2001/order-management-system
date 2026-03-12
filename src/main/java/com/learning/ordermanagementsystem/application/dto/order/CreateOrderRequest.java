package com.learning.ordermanagementsystem.application.dto.order;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateOrderRequest {
    private List<OrderItemRequest> items;

    private String discountType;
}
