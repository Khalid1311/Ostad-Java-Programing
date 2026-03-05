package com.example.OstadAssignment14.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Must not be blank")
    private String name;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    @NotBlank(message = "Must not be blank")
    @Column(unique = true)
    private String sku;

    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be positive")
    private Double price;

    @NotNull(message = "Quantity cannot be null")
    @Min(value = 0, message = "Quantity must be zero or more")
    private Integer quantity;

    @NotNull(message = "Status cannot be null")
    @Enumerated(EnumType.STRING)
    private ProductStatus status;
}
