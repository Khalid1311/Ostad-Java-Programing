package com.example.OstadAssignment27.dto.request;

import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockRequest {

    @Positive(message = "Quantity must be greater than zero")
    private Integer quantity;

}
