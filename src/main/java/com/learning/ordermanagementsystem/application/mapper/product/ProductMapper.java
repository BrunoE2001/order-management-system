package com.learning.ordermanagementsystem.application.mapper.product;

import com.learning.ordermanagementsystem.application.dto.product.CreateProductRequest;
import com.learning.ordermanagementsystem.domain.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(CreateProductRequest request);

    CreateProductRequest toResponse(Product product);
}
