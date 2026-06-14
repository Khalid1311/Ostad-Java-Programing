package com.example.Ostadassignment25.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShareResponse {

    private String message;
    private String downloadLink;
}
