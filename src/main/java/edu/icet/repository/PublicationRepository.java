package edu.icet.repository;

import edu.icet.entity.PublicationEntity;
import org.springframework.data.repository.CrudRepository;

public interface PublicationRepository extends CrudRepository<PublicationEntity, String> {
}
