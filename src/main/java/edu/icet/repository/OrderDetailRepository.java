package edu.icet.repository;

import edu.icet.entity.OrderDetailEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrderDetailRepository extends CrudRepository<OrderDetailEntity, Integer> {
    Iterable<OrderDetailEntity> findAllByOrderId(String id);
}
