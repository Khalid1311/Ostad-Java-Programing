package com.example.OatadAssignment16.service;

import com.example.OatadAssignment16.entity.Product;
import com.example.OatadAssignment16.exception.ResourceNotFoundException;
import com.example.OatadAssignment16.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

   @Test
   void saveProduct(){
       Product product = new Product();
       product.setName("Mouse");
       product.setQuantity(10);
       product.setPrice(20.0);

       when(productRepository.save(any(Product.class))).thenReturn(product);

       Product resultProduct = productService.createProduct(product);

       assertEquals(product, resultProduct);
       verify(productRepository).save(product);
   }

    @Test
    void getProduct_found() {
        Product product = new Product();
        product.setName("Mouse");
        product.setQuantity(10);
        product.setPrice(20.0);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product result = productService.getProductById(1L);

        assertEquals(product.getName(), result.getName());
    }

    @Test
    void getProduct_notFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> productService.getProductById(1L));
    }
}