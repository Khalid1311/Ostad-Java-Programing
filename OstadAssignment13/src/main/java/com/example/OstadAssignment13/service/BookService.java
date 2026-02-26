package com.example.OstadAssignment13.service;

import com.example.OstadAssignment13.entity.Book;
import com.example.OstadAssignment13.entity.Genre;
import com.example.OstadAssignment13.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;


    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Book not found"));
    }

    public Book updateBook(Long id, Book updatedBook) {
        Book book = getBookById(id);

        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setPublication(updatedBook.getPublication());
        book.setPublicationYear(updatedBook.getPublicationYear());
        book.setAvailableCopies(updatedBook.getAvailableCopies());
        book.setGenre(updatedBook.getGenre());

        return bookRepository.save(book);
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> getByAuthor(String authorName) {
        return bookRepository.findByAuthor(authorName);
    }

    public List<Book> getByGenre(Genre genre) {
        return bookRepository.findByGenre(genre);
    }

    public List<Book> getByPublication(String publication) {
        return bookRepository.findByPublication(publication);
    }

    public Map<String, Object> getByPublicationSummary(String publication) {
        List<Book> books = bookRepository.findByPublication(publication);

        int totalBooks = books.size();
        int totalCopies = books.stream().mapToInt(Book :: getAvailableCopies).sum();

        Map<String,Object> summary = new HashMap<>();
        summary.put("totalBooks", totalBooks);
        summary.put("totalAvailableCopies", totalCopies);

        return summary;
    }

    public long getTotalByGenre(Genre genre) {
        return bookRepository.countByGenre(genre);
    }
}
