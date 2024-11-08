package edu.icet.repository;

import edu.icet.entity.AuthorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthorRepository extends CrudRepository<AuthorEntity, String> {
    Optional<AuthorEntity> findByName(String name);
    @Query(value = "SELECT MAX(id) FROM Author", nativeQuery = true)
    Optional<String> findTopId();
}
