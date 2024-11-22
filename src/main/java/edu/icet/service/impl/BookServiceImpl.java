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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
        return setDTOInstances(bookEntity);
    }

    @Override
    public void update(Book book, MultipartFile image) throws IOException {
        repository.save(setEntityInstances(mapper.convertValue(book, BookEntity.class), book, image));
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return iterableToList(repository.findAll());
    }

    @Override
    public List<Book> getBooksByOwner(String ownerUserId) {
        return iterableToList(repository.findByOwnerUser(ownerUserId));
    }

    @Override
    public void updateQuantity(String id, int newQuantity) {
        Optional<BookEntity> bookEntity = repository.findById(id);
        if (bookEntity.isPresent()) {
            bookEntity.get().setQuantity(newQuantity);
            repository.save(bookEntity.get());
        }
    }

    @Override
    public List<Book> filterBooksByTitle(String title) {
        return iterableToList(repository.findByTitle("%"+title+"%"));
    }

    @Override
    public List<Book> filterBooksByAuthor(String author) {
        return iterableToList(repository.findByAuthor("%"+author+"%"));
    }

    @Override
    public Book filterBooksByIsbn(String isbn) {
        return setDTOInstances(repository.findByIsbn(isbn));
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

        if (null!=image){
            String imageName = book.getId() + FileSaveUtil.getFileExtension(image.getOriginalFilename());

            //Set image file path to bookEntity
            bookEntity.setImage(imageName);

            //Save image in the file system
            FileSaveUtil.saveFile("F:/iCET/Individual Project/images/", imageName, image);
        }

        return bookEntity;
    }

    private Book setDTOInstances(BookEntity bookEntity) {
        Book book = mapper.convertValue(bookEntity, Book.class);

        //Set relationship objects to references
        book.setGenre(mapper.convertValue(bookEntity.getGenre(), Genre.class));
        book.setAuthor(mapper.convertValue(bookEntity.getAuthor(), Author.class));
        book.setPublication(mapper.convertValue(bookEntity.getPublication(), Publication.class));
        book.setOwnerUser(mapper.convertValue(bookEntity.getOwnerUser(), User.class));

        return book;
    }

    private List<Book> iterableToList(Iterable<BookEntity> bookEntityIterable) {
        List<Book> bookList = new ArrayList<>();
        bookEntityIterable.forEach(bookEntity -> bookList.add(setDTOInstances(bookEntity)));
        return bookList;
    }
}
