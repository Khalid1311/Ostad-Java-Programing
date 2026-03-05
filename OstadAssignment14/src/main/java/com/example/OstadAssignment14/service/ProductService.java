package com.example.OstadAssignment14.service;

import com.example.OstadAssignment14.entity.Product;
import com.example.OstadAssignment14.exception.InvalidSkuFormatException;
import com.example.OstadAssignment14.exception.ProductNotFoundException;
import com.example.OstadAssignment14.exception.SkuAlreadyExistsException;
import com.example.OstadAssignment14.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final String SKU_PATTERN = "SKU-[A-Za-z0-9]{8}";

    public Product createProduct(Product product) {
        validateSku(product.getSku());
        if (productRepository.existsBySku(product.getSku())) {
            throw new SkuAlreadyExistsException("SKU already exists");
        }
        Product saved = productRepository.save(product);
        log.info("Product created with ID: {} and SKU: {}", saved.getId(), saved.getSku());
        return saved;
    }


    private void validateSku(String sku) {

        if (!Pattern.matches(SKU_PATTERN, sku)) {
            throw new InvalidSkuFormatException("Invalid SKU format. Example: SKU-A1B2C3D4");
        }
    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id "+id));
    }

    public Product updateProduct(Long id, Product product) {
        Product existing = getProductById(id);

        if(!existing.getSku().equals(product.getSku())){
            throw new InvalidSkuFormatException("SKU cannot be changed");
        }

        existing.setName(product.getName());
        existing.setDescription(product.getDescription());
        existing.setPrice(product.getPrice());
        existing.setQuantity(product.getQuantity());
        existing.setStatus(product.getStatus());

        return productRepository.save(existing);
    }

    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
        log.info("Product deleted with ID: {}", id);
    }
}

