package com.example.OstadAssignment27.mapper;

import com.example.OstadAssignment27.dto.request.ProductCreateRequest;
import com.example.OstadAssignment27.dto.response.ProductResponse;
import com.example.OstadAssignment27.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(ProductCreateRequest request) {

        return Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .currentStock(0)
                .build();
    }

    public ProductResponse toResponse(Product product) {

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .currentStock(product.getCurrentStock())
                .build();
    }
}