package com.example.OstadAssignment13.repository;

import com.example.OstadAssignment13.entity.Book;
import com.example.OstadAssignment13.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    List<Book> findByAuthor(String author);

    List<Book> findByGenre(Genre genre);

    List<Book> findByPublication(String publication);

    long countByGenre(Genre genre);
}
