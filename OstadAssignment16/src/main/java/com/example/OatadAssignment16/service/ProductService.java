package com.example.OatadAssignment16.service;

import com.example.OatadAssignment16.entity.Product;
import com.example.OatadAssignment16.exception.ResourceNotFoundException;
import com.example.OatadAssignment16.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    public Product updateStock(Long id, Integer quantity) {
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product not found"));
        product.setQuantity(quantity);
        return productRepository.save(product);
    }
}
