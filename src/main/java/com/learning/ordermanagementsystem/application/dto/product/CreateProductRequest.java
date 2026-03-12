package com.learning.ordermanagementsystem.application.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateProductRequest {
    @NotBlank(message = "El nombre del producto es requerido")
    private String name;
    @Positive(message = "El precio debe ser positivo")
    private BigDecimal price;
}
