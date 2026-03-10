package com.learning.ordermanagementsystem.api.controller;

import com.learning.ordermanagementsystem.domain.model.Product;
import com.learning.ordermanagementsystem.infrastructure.repository.JpaProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JpaProductRepository productRepository;

    @BeforeEach
    void setup() {

        productRepository.deleteAll();

        Product p1 = Product.builder()
                .name("Laptop")
                .price(BigDecimal.valueOf(1000))
                .build();

        Product p2 = Product.builder()
                .name("Mouse")
                .price(BigDecimal.valueOf(50))
                .build();

        productRepository.save(p1);
        productRepository.save(p2);
    }

    @Test
    void shouldReturnAllProducts() throws Exception {

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(2));
    }

    @Test
    void shouldFilterByName() throws Exception {

        mockMvc.perform(get("/products")
                        .param("name", "lap"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Laptop"));
    }

    @Test
    void shouldFilterByMinPrice() throws Exception {

        mockMvc.perform(get("/products")
                        .param("minPrice", "500"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(1))
                .andExpect(jsonPath("$.content[0].name").value("Laptop"));
    }

    @Test
    void shouldReturnPagedProducts() throws Exception {

        mockMvc.perform(get("/products")
                        .param("page", "0")
                        .param("size", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(1));
    }
}
