package edu.icet.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.dto.*;
import edu.icet.entity.*;
import edu.icet.repository.*;
import edu.icet.service.BookService;
import edu.icet.util.FileSaveUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository repository;
    private final ObjectMapper mapper;
    private final UserRepository userRepository;
    private final GenreRepository genreRepository;
    private final PublicationRepository publicationRepository;
    private final AuthorRepository authorRepository;

    @Override
    public void addBook(Book book, MultipartFile image) throws IOException {
        repository.save(setEntityInstances(mapper.convertValue(book, BookEntity.class), book, image));
    }

    @Override
    public Book getBookById(String id) {
        Optional<BookEntity> optionalBookEntity = repository.findById(id);
        //Check whether if is BookEntity available.
        if (optionalBookEntity.isEmpty()) return null;

        BookEntity bookEntity = optionalBookEntity.get();
        Book book = mapper.convertValue(bookEntity, Book.class);

        //Set relationship objects to references
        book.setGenre(mapper.convertValue(bookEntity.getGenre(), Genre.class));
        book.setAuthor(mapper.convertValue(bookEntity.getAuthor(), Author.class));
        book.setPublication(mapper.convertValue(bookEntity.getPublication(), Publication.class));
        book.setOwnerUser(mapper.convertValue(bookEntity.getOwnerUser(), User.class));

        return book;
    }

    @Override
    public void update(Book book, MultipartFile image) throws IOException {
        repository.save(setEntityInstances(mapper.convertValue(book, BookEntity.class), book, image));
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    //Set all instances to reference variables
    private BookEntity setEntityInstances(BookEntity bookEntity, Book book, MultipartFile image) throws IOException {
        //Set ownerUser to bookEntity
        bookEntity.setOwnerUser(mapper.convertValue(userRepository.findById(book.getOwnerUser().getId()), UserEntity.class));
        //Set genreEntity to bookEntity
        bookEntity.setGenre(mapper.convertValue(genreRepository.findById(book.getGenre().getId()), GenreEntity.class));
        //Set publicationEntity to bookEntity
        bookEntity.setPublication(mapper.convertValue(publicationRepository.findById(book.getPublication().getId()), PublicationEntity.class));
        //Set authorEntity to bookEntity
        bookEntity.setAuthor(mapper.convertValue(authorRepository.findById(book.getAuthor().getId()), AuthorEntity.class));

        //Set image file path to bookEntity
        String saveDir = "src/main/resources/static/images/book/";
        String originalFileName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
        String imageName = (book.getId() + "-" + originalFileName.replace(" ", "_"));
        bookEntity.setImage("/images/book/" + imageName);

        //Save image in the file system
        FileSaveUtil.saveFile(saveDir, imageName, image);

        return bookEntity;
    }
}
