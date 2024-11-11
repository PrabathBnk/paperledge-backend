package edu.icet.repository;

import edu.icet.entity.OrderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OrderRepository extends CrudRepository<OrderEntity, String> {
    @Query(value = "SELECT MAX(id) FROM orders", nativeQuery = true)
    Optional<String> findTopId();
    Iterable<OrderEntity> findAllByUserId(String id);
}
