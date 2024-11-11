package edu.icet.repository;

import edu.icet.entity.PaymentMethodEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PaymentMethodRepository extends CrudRepository<PaymentMethodEntity, Integer> {
    Optional<PaymentMethodEntity> findByName(String name);
}
