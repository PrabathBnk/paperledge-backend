package edu.icet.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.dto.Author;
import edu.icet.entity.AuthorEntity;
import edu.icet.repository.AuthorRepository;
import edu.icet.service.AuthorService;
import edu.icet.util.GenerateIdUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository repository;
    private final ObjectMapper mapper;

    @Override
    public Author getAuthorByName(String name) {
        Optional<AuthorEntity> optionalAuthorEntity = repository.findByName(name);
        return optionalAuthorEntity.isPresent() ? mapper.convertValue(optionalAuthorEntity.get(), Author.class): addAuthor(new Author(name));
    }

    @Override
    public Author addAuthor(Author author) {
        author.setId(GenerateIdUtil.generateId("AT", 3, repository.findTopId().orElse("000")));
        return mapper.convertValue(repository.save(mapper.convertValue(author, AuthorEntity.class)), Author.class);
    }
}
