package com.example.OstadAssignment14.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>>handleValidation(MethodArgumentNotValidException ex){
        log.error("Validation failed: {}", ex.getMessage());
        return ResponseEntity.badRequest().body(Map.of(
                "message : ",ex.getMessage()));

    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String,String>>handleNotFound( ProductNotFoundException ex){
        log.warn("Failed to find product: {}", ex.getMessage());
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(InvalidSkuFormatException.class)
    public ResponseEntity<Map<String,String>>handleInvalidSku( InvalidSkuFormatException ex){
        log.error(ex.getMessage());
        return ResponseEntity.badRequest().body(Map.of(
                "message : ",ex.getMessage()));

    }

    @ExceptionHandler(SkuAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Map<String,String>>handleSkuExists( SkuAlreadyExistsException ex){
        log.error(ex.getMessage());
        Map<String,String> handler = new HashMap<>();
        handler.put("message", ex.getMessage());
        return (ResponseEntity<Map<String, String>>) handler;
    }
}
