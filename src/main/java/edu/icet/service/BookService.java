package edu.icet.service;

import edu.icet.dto.Book;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BookService {
    void addBook(Book book, MultipartFile image) throws IOException;
    Book getBookById(String id);
    void update(Book book, MultipartFile image) throws IOException;
    void deleteById(String id);
    List<Book> getAllBooks();
    List<Book> getBooksByOwner(String ownerUserId);
}
