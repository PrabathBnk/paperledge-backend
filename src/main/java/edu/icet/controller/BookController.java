package edu.icet.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.dto.Book;
import edu.icet.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
@CrossOrigin
public class BookController {

    private final BookService service;
    private final ObjectMapper mapper;

    @PostMapping
    public ResponseEntity<Map<String, String>> persist(@RequestParam("book") String bookAsString, @RequestParam("image") MultipartFile bookImage){
        try {
            Book book = mapper.readValue(bookAsString, Book.class);
            service.addBook(book, bookImage);
            Map<String, String> response = new HashMap<>();
            response.put("response", "Book added successfully.");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @GetMapping
    public Book getBookById(@RequestParam("id") String id){
        return service.getBookById(id);
    }

    @PutMapping
    public ResponseEntity<Map<String, String>> update(@RequestParam("book") String bookAsString, @RequestParam(value = "image", required = false) MultipartFile bookImage){
        try {
            Book book = mapper.readValue(bookAsString, Book.class);
            service.update(book, bookImage);
            Map<String, String> response = new HashMap<>();
            response.put("response", "Book updated successfully.");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IOException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @DeleteMapping
    public void deleteById(@RequestParam("id") String id){
        service.deleteById(id);
    }

    @GetMapping("/all")
    public List<Book> getAll(){
        return service.getAllBooks();
    }

    @GetMapping("/filter/owner-user")
    public List<Book> filterBookByOwner(@RequestParam("ownerUserId") String ownerUserId) {
        return service.getBooksByOwner(ownerUserId);
    }

    @GetMapping("/filter/title")
    public List<Book> filterBookByTitle(@RequestParam("title") String title){
        return service.filterBooksByTitle(title);
    }

    @GetMapping("/filter/author")
    public List<Book> filterBookByAuthor(@RequestParam("author") String author){
        return service.filterBooksByAuthor(author);
    }

    @GetMapping("/filter/isbn")
    public Book filterBookByIsbn(@RequestParam("isbn") String isbn){
        return service.filterBooksByIsbn(isbn);
    }
}
