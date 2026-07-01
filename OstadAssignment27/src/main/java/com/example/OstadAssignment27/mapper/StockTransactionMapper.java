package com.example.OstadAssignment27.mapper;

import com.example.OstadAssignment27.dto.response.TransactionResponse;
import com.example.OstadAssignment27.entity.StockTransaction;
import org.springframework.stereotype.Component;

@Component
public class StockTransactionMapper {

    public TransactionResponse toResponse(StockTransaction transaction) {

        return TransactionResponse.builder()
                .id(transaction.getId())
                .type(transaction.getType())
                .quantity(transaction.getQuantity())
                .createdAt(transaction.getCreatedAt())
                .build();
    }
}