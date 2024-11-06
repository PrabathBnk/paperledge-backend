package edu.icet.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.dto.Book;
import edu.icet.entity.*;
import edu.icet.repository.*;
import edu.icet.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public void addBook(Book book) {
        //Convert Book into BookEntity
        BookEntity bookEntity = mapper.convertValue(book, BookEntity.class);

        //Set ownerUser to bookEntity
        bookEntity.setOwnerUser(mapper.convertValue(userRepository.findById(book.getOwnerUser().getId()), UserEntity.class));
        //Set genreEntity to bookEntity
        bookEntity.setGenre(mapper.convertValue(genreRepository.findById(book.getGenre().getId()), GenreEntity.class));
        //Set publicationEntity to bookEntity
        bookEntity.setPublication(mapper.convertValue(publicationRepository.findById(book.getPublication().getId()), PublicationEntity.class));
        //Set authorEntity to bookEntity
        bookEntity.setAuthor(mapper.convertValue(authorRepository.findById(book.getAuthor().getId()), AuthorEntity.class));

        repository.save(bookEntity);
    }
}
