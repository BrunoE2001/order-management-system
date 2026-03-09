package com.learning.ordermanagementsystem.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemRequest {
    private String productName;

    private Double price;

    private Integer quantity;
}
