package edu.icet.repository;

import edu.icet.entity.BookEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BookRepository extends CrudRepository<BookEntity, String> {
    @Query(value = "SELECT MAX(id) FROM Book", nativeQuery = true)
    Optional<String> findTopId();
    @Query(value = "SELECT * FROM Book WHERE owner_user=?", nativeQuery = true)
    Iterable<BookEntity> findByOwnerUser(String ownerUser);
}
