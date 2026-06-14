package com.example.Ostadassignment25.exception;

import com.example.Ostadassignment25.dto.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidOtpException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidOtp(
            InvalidOtpException ex) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ApiErrorResponse.builder()
                                .timestamp(LocalDateTime.now())
                                .message(ex.getMessage())
                                .status(HttpStatus.BAD_REQUEST.value())
                                .build()
                );
    }

    @ExceptionHandler(OtpAlreadyUsedException.class)
    public ResponseEntity<ApiErrorResponse> handleOtpUsed(
            OtpAlreadyUsedException ex) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ApiErrorResponse.builder()
                                .timestamp(LocalDateTime.now())
                                .message(ex.getMessage())
                                .status(HttpStatus.BAD_REQUEST.value())
                                .build()
                );
    }

    @ExceptionHandler(FileExpiredException.class)
    public ResponseEntity<ApiErrorResponse> handleExpired(
            FileExpiredException ex) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ApiErrorResponse.builder()
                                .timestamp(LocalDateTime.now())
                                .message(ex.getMessage())
                                .status(HttpStatus.BAD_REQUEST.value())
                                .build()
                );
    }

    @ExceptionHandler(FileStorageException.class)
    public ResponseEntity<ApiErrorResponse> handleStorage(
            FileStorageException ex) {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ApiErrorResponse.builder()
                                .timestamp(LocalDateTime.now())
                                .message(ex.getMessage())
                                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                .build()
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGeneral(
            Exception ex) {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ApiErrorResponse.builder()
                                .timestamp(LocalDateTime.now())
                                .message(ex.getMessage())
                                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                .build()
                );
    }
}