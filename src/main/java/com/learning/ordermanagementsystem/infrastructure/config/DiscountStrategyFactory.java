package com.learning.ordermanagementsystem.infrastructure.config;

import com.learning.ordermanagementsystem.domain.strategy.DiscountStrategy;
import com.learning.ordermanagementsystem.domain.strategy.NoDiscountStrategy;
import com.learning.ordermanagementsystem.domain.strategy.SeasonalDiscountStrategy;
import com.learning.ordermanagementsystem.domain.strategy.VipDiscountStrategy;
import org.springframework.stereotype.Component;

@Component
public class DiscountStrategyFactory {
    public DiscountStrategy getStrategy(String type) {

        return switch (type) {
            case "VIP" -> new VipDiscountStrategy();
            case "SEASONAL" -> new SeasonalDiscountStrategy();
            default -> new NoDiscountStrategy();
        };
    }
}
