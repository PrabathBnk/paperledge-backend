package edu.icet.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.dto.Genre;
import edu.icet.entity.GenreEntity;
import edu.icet.repository.GenreRepository;
import edu.icet.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    public final GenreRepository repository;
    public final ObjectMapper mapper;

    @Override
    public List<Genre> getAllGenres() {
        Iterable<GenreEntity> genreEntitieInIterable = repository.findAll();
        List<Genre> genreList = new ArrayList<>();

        genreEntitieInIterable.forEach(genreEntity -> genreList.add(mapper.convertValue(genreEntity, Genre.class)));

        return genreList;
    }
}
