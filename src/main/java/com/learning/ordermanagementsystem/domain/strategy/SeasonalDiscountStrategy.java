package com.learning.ordermanagementsystem.domain.strategy;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
public class SeasonalDiscountStrategy implements DiscountStrategy {
    @Override
    public BigDecimal applyDiscount(BigDecimal total) {
        return total.multiply(BigDecimal.valueOf(0.85));
    }
}
