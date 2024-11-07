package edu.icet.service;

import edu.icet.dto.Book;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface BookService {
    void addBook(Book book, MultipartFile image) throws IOException;
    Book getBookById(String id);
}
