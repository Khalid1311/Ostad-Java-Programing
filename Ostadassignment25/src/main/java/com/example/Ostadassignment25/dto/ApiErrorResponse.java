package com.example.Ostadassignment25.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiErrorResponse {

    private LocalDateTime timestamp;

    private String message;

    private int status;
}
