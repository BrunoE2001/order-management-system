package com.learning.ordermanagementsystem.domain.strategy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class NoDiscountStrategy implements DiscountStrategy {
    @Override
    public BigDecimal applyDiscount(BigDecimal total) {
        return total;
    }
}
