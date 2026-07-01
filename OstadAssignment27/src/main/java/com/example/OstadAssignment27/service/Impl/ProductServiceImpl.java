package com.example.OstadAssignment27.service.Impl;

import com.example.OstadAssignment27.dto.request.ProductCreateRequest;
import com.example.OstadAssignment27.dto.request.StockRequest;
import com.example.OstadAssignment27.dto.response.ProductResponse;
import com.example.OstadAssignment27.dto.response.StockResponse;
import com.example.OstadAssignment27.dto.response.TransactionResponse;
import com.example.OstadAssignment27.entity.Product;
import com.example.OstadAssignment27.entity.StockTransaction;
import com.example.OstadAssignment27.entity.type.TransactionType;
import com.example.OstadAssignment27.exception.InsufficientStockException;
import com.example.OstadAssignment27.exception.ProductNotFoundException;
import com.example.OstadAssignment27.mapper.ProductMapper;
import com.example.OstadAssignment27.mapper.StockTransactionMapper;
import com.example.OstadAssignment27.repository.ProductRepository;
import com.example.OstadAssignment27.repository.StockTransactionRepository;
import com.example.OstadAssignment27.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final StockTransactionRepository stockTransactionRepository;
    private final StockTransactionMapper stockTransactionMapper;

    @Override
    public ProductResponse createProduct(ProductCreateRequest request) {

        Product product = productMapper.toEntity(request);

        Product savedProduct = productRepository.save(product);

        return productMapper.toResponse(savedProduct);
    }

    @Override
    public List<ProductResponse> getAllProducts() {

        return productRepository.findAll()
                .stream()
                .map(productMapper::toResponse)
                .toList();
    }

    @Override
    public ProductResponse getProductById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found with id : " + id));

        return productMapper.toResponse(product);
    }

    @Override
    public StockResponse stockIn(Long productId, StockRequest request) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found"));

        product.setCurrentStock(
                product.getCurrentStock() + request.getQuantity()
        );

        productRepository.save(product);

        StockTransaction transaction = StockTransaction.builder()
                .product(product)
                .quantity(request.getQuantity())
                .type(TransactionType.IN)
                .createdAt(LocalDateTime.now())
                .build();

        stockTransactionRepository.save(transaction);

        return StockResponse.builder()
                .productId(product.getId())
                .currentStock(product.getCurrentStock())
                .build();
    }

    @Override
    public StockResponse stockOut(Long productId, StockRequest request) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found"));

        if(product.getCurrentStock() < request.getQuantity()){
            throw new InsufficientStockException("Insufficient Stock");
        }

        product.setCurrentStock(
                product.getCurrentStock() - request.getQuantity()
        );

        productRepository.save(product);

        StockTransaction transaction = StockTransaction.builder()
                .product(product)
                .quantity(request.getQuantity())
                .type(TransactionType.OUT)
                .createdAt(LocalDateTime.now())
                .build();

        stockTransactionRepository.save(transaction);

        return StockResponse.builder()
                .productId(product.getId())
                .currentStock(product.getCurrentStock())
                .build();
    }

    @Override
    public StockResponse getCurrentStock(Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found"));

        return StockResponse.builder()
                .productId(product.getId())
                .currentStock(product.getCurrentStock())
                .build();
    }

    @Override
    public List<TransactionResponse> getTransactionHistory(Long productId) {

        productRepository.findById(productId)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found"));

        return stockTransactionRepository.findByProductId(productId)
                .stream()
                .map(stockTransactionMapper::toResponse)
                .toList();
    }
}