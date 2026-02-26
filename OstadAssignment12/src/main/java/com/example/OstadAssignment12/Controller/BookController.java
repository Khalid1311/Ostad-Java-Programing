package com.example.OstadAssignment12.Controller;

import com.example.OstadAssignment12.Entity.Book;
import com.example.OstadAssignment12.Entity.Genre;
import com.example.OstadAssignment12.Repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        return ResponseEntity.status(HttpStatus.CREATED.value())
                .body(bookRepository.save(book));
    }

    @GetMapping
    public List<Book> getAllBook(){
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        return ResponseEntity.ok(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id, @RequestBody Book Updatebook){
        Optional<Book> optionalBook = bookRepository.findById(id);

        if(optionalBook.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Book with id "+ id + "Not found");
        }

        Book book = optionalBook.get();
        book.setTitle(Updatebook.getTitle());
        book.setAuthor(Updatebook.getAuthor());
        book.setPublication(Updatebook.getPublication());
        book.setPublicationYear(Updatebook.getPublicationYear());
        book.setAvailableCopies(Updatebook.getAvailableCopies());
        book.setGenre(Updatebook.getGenre());

        return ResponseEntity.ok().body(bookRepository.save(book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        if(!bookRepository.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Book with id "+ id + "Not found");
        }
        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/author/{authorName}")
    public List<Book> findByAuthor(@PathVariable String authorName){
        return bookRepository.findAll().stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(authorName))
                .collect(Collectors.toList());
    }

    @GetMapping("/genre/{genre}")
    public List<Book> findByGenre(@PathVariable Genre genre){
        return bookRepository.findAll().stream()
                .filter(book -> book.getGenre() == genre)
                .collect(Collectors.toList());
    }

    @GetMapping("/publication/{publication}")
    public List<Book> findByPublication(@PathVariable String publication) {
        return bookRepository.findAll()
                .stream()
                .filter(book -> book.getPublication().equalsIgnoreCase(publication))
                .collect(Collectors.toList());
    }

    @GetMapping("/publication/{publication}/summary")
    public String publicationSummary(@PathVariable String publication){
        List<Book> book = findByPublication(publication);

        long totalBooks = book.size();
        int totalCopies = book.stream().map(Book::getAvailableCopies)
                .reduce(0,Integer::sum);

        return "totalBooks : " + totalBooks +"\n" +
                "totalAvailableCopies : " +totalCopies;
    }



}
