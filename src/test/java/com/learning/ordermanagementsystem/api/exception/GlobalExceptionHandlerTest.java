package com.learning.ordermanagementsystem.api.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.ordermanagementsystem.api.controller.ProductController;
import com.learning.ordermanagementsystem.application.dto.product.CreateProductRequest;
import com.learning.ordermanagementsystem.application.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@Import(GlobalExceptionHandler.class)
@WebMvcTest(
        ProductController.class
)
class GlobalExceptionHandlerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ProductService productService;

    @Test
    @DisplayName("ConstraintViolationException")
    void shouldReturnConstraintViolation() throws Exception {

        mockMvc.perform(get("/products")
                        .param("minPrice", "-1"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400));
    }

    @Test
    @DisplayName("MethodArgumentNotValidException")
    void shouldReturnValidationError() throws Exception {

        CreateProductRequest request = new CreateProductRequest();
        request.setName("");
        request.setPrice(BigDecimal.valueOf(-10));

        mockMvc.perform(post("/products")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.errors").isArray());
    }

    @Test
    @DisplayName("Method Not Allowed")
    void shouldReturnMethodNotAllowed() throws Exception {
        mockMvc.perform(patch("/products"))
                .andExpect(status().isMethodNotAllowed())
                .andExpect(jsonPath("$.status").value(405));
    }

    @Test
    @DisplayName("handleRuntimeException")
    void shouldRuntimeException() throws Exception {
        Mockito.when(productService.getByIdProduct(anyLong()))
                .thenThrow(new RuntimeException("Error interno inesperado en el servidor"));

        mockMvc.perform(get("/products/1"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status").value(500))
                .andExpect(jsonPath("$.message").value("Error interno inesperado en el servidor"));
    }
}
