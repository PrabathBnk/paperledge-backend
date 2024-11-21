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
    @Query(value = "SELECT * FROM book WHERE title LIKE ?", nativeQuery = true)
    Iterable<BookEntity> findByTitle(String title);
    @Query(value = "SELECT book.* FROM book INNER JOIN author ON book.author_id = author.id WHERE author.name LIKE ?", nativeQuery = true)
    Iterable<BookEntity> findByAuthor(String author);
    BookEntity findByIsbn(String isbn);
}
