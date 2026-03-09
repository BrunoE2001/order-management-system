package com.learning.ordermanagementsystem.domain.strategy;

import java.math.BigDecimal;

public interface DiscountStrategy {
    BigDecimal applyDiscount(BigDecimal total);
}
