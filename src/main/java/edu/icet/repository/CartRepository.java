package edu.icet.repository;

import edu.icet.entity.CartEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CartRepository extends CrudRepository<CartEntity, String> {
    Iterable<CartEntity> findAllByUserId(String id);
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM cart_item WHERE user_id=? AND book_id=?", nativeQuery = true)
    void deleteCartItem(String userId, String bookId);
    @Query(value = "SELECT * FROM cart_item WHERE user_id=? AND book_id=?", nativeQuery = true)
    Optional<CartEntity> findByUserIdAndBookId(String userId, String bookId);
}
