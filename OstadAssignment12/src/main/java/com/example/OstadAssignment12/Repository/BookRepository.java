package com.example.OstadAssignment12.Repository;

import com.example.OstadAssignment12.Entity.Book;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {}
