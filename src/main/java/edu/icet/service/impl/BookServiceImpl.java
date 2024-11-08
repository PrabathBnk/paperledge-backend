package edu.icet.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.dto.*;
import edu.icet.entity.*;
import edu.icet.repository.*;
import edu.icet.service.AuthorService;
import edu.icet.service.BookService;
import edu.icet.service.PublicationService;
import edu.icet.util.FileSaveUtil;
import edu.icet.util.GenerateIdUtil;
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
    private final PublicationService publicationService;
    private final AuthorService authorService;

    @Override
    public void addBook(Book book, MultipartFile image) throws IOException {
        book.setId(GenerateIdUtil.generateId("BK", 4, repository.findTopId().orElse("000")));
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
        bookEntity.setPublication(mapper.convertValue(publicationService.getPublicationByName(book.getPublication().getName()), PublicationEntity.class));
        //Set authorEntity to bookEntity
        bookEntity.setAuthor(mapper.convertValue(authorService.getAuthorByName(book.getAuthor().getName()), AuthorEntity.class));

        //Set image file path to bookEntity
        String saveDir = "src/main/resources/static/images/book/";

        String imageName = createBookName(book, StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename())));
        bookEntity.setImage("/images/book/" + imageName);

        //Save image in the file system
        FileSaveUtil.saveFile(saveDir, imageName, image);

        return bookEntity;
    }

    private String createBookName(Book book, String originalName){
        String[] splitStrings = originalName.split("\\.");
        return (book.getId() + "-" + book.getTitle() + "." + splitStrings[splitStrings.length - 1].replace(" ", "_"));
    }
}
