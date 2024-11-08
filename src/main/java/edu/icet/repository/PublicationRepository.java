package edu.icet.repository;

import edu.icet.entity.PublicationEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublicationRepository extends CrudRepository<PublicationEntity, String> {
    Optional<PublicationEntity> findByName(String name);
    @Query(value = "SELECT MAX(id) FROM Publication", nativeQuery = true)
    Optional<String> findTopId();
}
