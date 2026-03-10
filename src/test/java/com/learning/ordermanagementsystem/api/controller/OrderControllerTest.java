package com.learning.ordermanagementsystem.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.ordermanagementsystem.application.dto.CreateOrderRequest;
import com.learning.ordermanagementsystem.application.dto.OrderItemRequest;
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
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JpaProductRepository productRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        Product product = Product.builder()
                .name("Laptop")
                .price(BigDecimal.valueOf(1000))
                .build();

        productRepository.save(product);
    }

    @Test
    void shouldCreateOrder() throws Exception {

        OrderItemRequest item = new OrderItemRequest();
        item.setProductName("Laptop");
        item.setPrice(1000.0);
        item.setQuantity(1);

        CreateOrderRequest request = new CreateOrderRequest();
        request.setDiscountType("NORMAL");
        request.setItems(List.of(item));

        mockMvc.perform(post("/orders")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.total").exists())
                .andExpect(jsonPath("$.status").value("CREATED"));
    }

    @Test
    void shouldCreateOrderVIP() throws Exception {

        OrderItemRequest item = new OrderItemRequest();
        item.setProductName("Laptop");
        item.setPrice(1000.0);
        item.setQuantity(1);

        CreateOrderRequest request = new CreateOrderRequest();
        request.setDiscountType("VIP");
        request.setItems(List.of(item));

        mockMvc.perform(post("/orders")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.total").exists())
                .andExpect(jsonPath("$.status").value("CREATED"));
    }

    @Test
    void shouldCreateOrderSEASONAL() throws Exception {

        OrderItemRequest item = new OrderItemRequest();
        item.setProductName("Laptop");
        item.setPrice(1000.0);
        item.setQuantity(1);

        CreateOrderRequest request = new CreateOrderRequest();
        request.setDiscountType("SEASONAL");
        request.setItems(List.of(item));

        mockMvc.perform(post("/orders")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.total").exists())
                .andExpect(jsonPath("$.status").value("CREATED"));
    }
}
