package com.example.OstadAssignment27.controller;

import com.example.OstadAssignment27.dto.request.ProductCreateRequest;
import com.example.OstadAssignment27.dto.request.StockRequest;
import com.example.OstadAssignment27.dto.response.ProductResponse;
import com.example.OstadAssignment27.dto.response.StockResponse;
import com.example.OstadAssignment27.dto.response.TransactionResponse;
import com.example.OstadAssignment27.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ProductResponse createProduct(
            @Valid @RequestBody ProductCreateRequest request
    ) {
        return productService.createProduct(request);
    }

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(
            @PathVariable Long id
    ) {
        return productService.getProductById(id);
    }

    @PostMapping("/{id}/stock-in")
    public StockResponse stockIn(
            @PathVariable Long id,
            @Valid @RequestBody StockRequest request){

        return productService.stockIn(id,request);
    }

    @PostMapping("/{id}/stock-out")
    public StockResponse stockOut(
            @PathVariable Long id,
            @Valid @RequestBody StockRequest request){

        return productService.stockOut(id,request);
    }

    @GetMapping("/{id}/stock")
    public StockResponse currentStock(
            @PathVariable Long id){

        return productService.getCurrentStock(id);
    }

    @GetMapping("/{id}/transactions")
    public List<TransactionResponse> history(
            @PathVariable Long id){

        return productService.getTransactionHistory(id);
    }

}