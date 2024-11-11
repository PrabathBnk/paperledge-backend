package edu.icet.repository;

import edu.icet.entity.StatusEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StatusRepository extends CrudRepository<StatusEntity, Integer> {
    Optional<StatusEntity> findByName(String name);
}
