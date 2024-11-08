package edu.icet.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.dto.Publication;
import edu.icet.entity.PublicationEntity;
import edu.icet.repository.PublicationRepository;
import edu.icet.service.PublicationService;
import edu.icet.util.GenerateIdUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PublicationServiceImpl implements PublicationService {

    private final PublicationRepository repository;
    private final ObjectMapper mapper;

    @Override
    public Publication getPublicationByName(String name) {
        Optional<PublicationEntity> optionalPublicationEntity = repository.findByName(name);
        return optionalPublicationEntity.isPresent() ? mapper.convertValue(optionalPublicationEntity.get(), Publication.class): addPublication(new Publication(name));
    }

    @Override
    public Publication addPublication(Publication publication) {
        publication.setId(GenerateIdUtil.generateId("PUB", 3, repository.findTopId().orElse("000")));
        return mapper.convertValue(repository.save(mapper.convertValue(publication, PublicationEntity.class)), Publication.class);
    }
}
