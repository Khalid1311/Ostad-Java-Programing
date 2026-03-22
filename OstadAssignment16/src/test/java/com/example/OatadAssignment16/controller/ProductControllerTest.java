package com.example.OatadAssignment16.controller;

import com.example.OatadAssignment16.entity.Product;
import com.example.OatadAssignment16.exception.ResourceNotFoundException;
import com.example.OatadAssignment16.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    @Test
    void saveProduct() throws Exception {
        Product product = new Product();
        product.setName("Mouse");
        product.setQuantity(10);
        product.setPrice(20.0);

        when(productService.createProduct(any()))
                .thenReturn(product);

        String json = """
                {
                  "name": "Mouse",
                  "quantity": 10,
                  "price": 20.0
                }
                """;

        mockMvc.perform(post("/api/products")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    void getProduct() throws Exception {
        Product product = new Product();
        product.setName("Mouse");
        product.setQuantity(10);
        product.setPrice(20.0);

        Mockito.when(productService.getProductById(1L))
                .thenReturn(product);

        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getProduct_notFound() throws Exception {
        Mockito.when(productService.getProductById(1L))
                .thenThrow(new ResourceNotFoundException("Not Found"));

        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isNotFound());
    }

}