package com.example.OstadAssignment27.dto.response;

import com.example.OstadAssignment27.entity.type.TransactionType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class TransactionResponse {

    private Long id;

    private TransactionType type;

    private Integer quantity;

    private LocalDateTime createdAt;

}