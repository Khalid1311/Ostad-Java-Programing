package com.example.OstadAssignment13.controller;

import com.example.OstadAssignment13.entity.Book;
import com.example.OstadAssignment13.entity.Genre;
import com.example.OstadAssignment13.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public Book addBook(@RequestBody Book book){
        return bookService.addBook(book);
    }

    @GetMapping
    public List<Book> gelAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book gelAllBooks(@PathVariable Long id){
        return bookService.getBookById(id);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id,@RequestBody Book book) {
        return bookService.updateBook(id, book);
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable Long id){
        bookService.deleteBookById(id);
    }

    @GetMapping("/author/{authorName}")
    public List<Book> getByAuthor(@PathVariable String authorName){
        return bookService.getByAuthor(authorName);
    }

    @GetMapping("/genre/{genre}")
    public List<Book> getByGenre(@PathVariable Genre genre){
        return bookService.getByGenre(genre);
    }

    @GetMapping("/publication/{publication}")
    public List<Book> getByPublication(@PathVariable String publication){
        return bookService.getByPublication(publication);
    }

    @GetMapping("/publication/{publication}/summary")
    public Map<String, Object> getByPublicationSummary(@PathVariable String publication){
        return bookService.getByPublicationSummary(publication);
    }

    @GetMapping("/genre/{genre}/total")
    public long getTotalByGenre(@PathVariable Genre genre) {
        return bookService.getTotalByGenre(genre);
    }
}
