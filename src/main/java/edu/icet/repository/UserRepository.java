package edu.icet.repository;

import edu.icet.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, String> {
    Optional<UserEntity> findByEmail(String email);
    @Query(value = "SELECT MAX(id) FROM Users", nativeQuery = true)
    Optional<String> findTopId();
    Optional<UserEntity> findByUsername(String userName);
}
