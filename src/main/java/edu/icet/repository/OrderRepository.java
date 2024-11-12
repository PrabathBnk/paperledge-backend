package edu.icet.repository;

import edu.icet.entity.OrderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OrderRepository extends CrudRepository<OrderEntity, String> {
    @Query(value = "SELECT MAX(id) FROM orders", nativeQuery = true)
    Optional<String> findTopId();
    Iterable<OrderEntity> findAllByUserId(String id);
    @Query(value = "SELECT DISTINCT orders.* FROM book INNER JOIN order_detail ON book.id=order_detail.book_id INNER JOIN orders ON order_detail.order_id=orders.id WHERE book.owner_user=?;", nativeQuery = true)
    Iterable<OrderEntity> findAllByBookOwnerUserId(String id);
}
