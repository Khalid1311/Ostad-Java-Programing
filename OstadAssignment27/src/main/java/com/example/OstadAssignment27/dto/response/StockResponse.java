package com.example.OstadAssignment27.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class StockResponse {

    private Long productId;

    private Integer currentStock;

}