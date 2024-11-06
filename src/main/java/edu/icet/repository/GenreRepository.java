package edu.icet.repository;

import edu.icet.entity.GenreEntity;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<GenreEntity, String> {
}
