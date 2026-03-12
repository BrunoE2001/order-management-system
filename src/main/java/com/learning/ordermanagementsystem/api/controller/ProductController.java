package com.learning.ordermanagementsystem.api.controller;

import com.learning.ordermanagementsystem.application.dto.product.CreateProductRequest;
import com.learning.ordermanagementsystem.application.service.ProductService;
import com.learning.ordermanagementsystem.domain.model.Product;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.net.URI;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Validated
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<Product>> getProduct(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) @Positive BigDecimal minPrice,
            @RequestParam(required = false) @Positive BigDecimal maxPrice,
            @PageableDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Page<Product> result = productService.getAllProducts(name, minPrice, maxPrice, pageable);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        Product product = productService.getByIdProduct(productId);

        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Void> createProduct(@Valid @RequestBody CreateProductRequest product) {
        Long id = productService.saveProduct(product);
        URI location = URI.create("/products/" + id);

        return ResponseEntity.created(location).build();
    }
}
