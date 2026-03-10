package com.learning.ordermanagementsystem.infrastructure.specification;

import com.learning.ordermanagementsystem.domain.model.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpecification {
    public static Specification<Product> hasName(String name) {

        return (root, query, cb) -> {

            if (name == null || name.isBlank()) {
                return cb.conjunction();
            }

            return cb.like(
                    cb.lower(root.get("name")),
                    "%" + name.toLowerCase() + "%"
            );
        };
    }

    public static Specification<Product> minPrice(BigDecimal minPrice) {
        return (root, query, cb) ->
            minPrice == null ? cb.conjunction() : cb.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    public static Specification<Product> maxPrice(BigDecimal maxPrice) {
        return (root, query, cb) ->
                maxPrice == null ? cb.conjunction() : cb.lessThanOrEqualTo(root.get("price"), maxPrice);
    }
}
