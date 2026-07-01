package com.example.OstadAssignment27.service;

import com.example.OstadAssignment27.dto.request.ProductCreateRequest;
import com.example.OstadAssignment27.dto.request.StockRequest;
import com.example.OstadAssignment27.dto.response.ProductResponse;
import com.example.OstadAssignment27.dto.response.StockResponse;
import com.example.OstadAssignment27.dto.response.TransactionResponse;

import java.util.List;

public interface ProductService {

    ProductResponse createProduct(ProductCreateRequest request);

    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(Long id);

    StockResponse stockIn(Long productId, StockRequest request);

    StockResponse stockOut(Long productId, StockRequest request);

    StockResponse getCurrentStock(Long productId);

    List<TransactionResponse> getTransactionHistory(Long productId);

}