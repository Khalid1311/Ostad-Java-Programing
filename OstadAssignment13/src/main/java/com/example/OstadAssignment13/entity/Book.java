package com.example.OstadAssignment13.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String author;
    private String publication;
    private int publicationYear;
    private int availableCopies;

    @Enumerated(EnumType.STRING)
    private Genre genre;
}
