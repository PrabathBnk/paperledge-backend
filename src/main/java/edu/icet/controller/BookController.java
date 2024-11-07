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

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
@CrossOrigin
public class BookController {

    private final BookService service;
    private final ObjectMapper mapper;

    @PostMapping
    public ResponseEntity<String> persist(@RequestParam("book") String bookAsString, @RequestParam("image") MultipartFile bookImage){
        try {
            Book book = mapper.readValue(bookAsString, Book.class);
            service.addBook(book, bookImage);
            return new ResponseEntity<>("Book added successfully", HttpStatus.ACCEPTED);
        } catch (IOException e) {
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public Book getBookById(@RequestParam("id") String id){
        return service.getBookById(id);
    }
}
