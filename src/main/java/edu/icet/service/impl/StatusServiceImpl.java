package edu.icet.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.dto.Status;
import edu.icet.repository.StatusRepository;
import edu.icet.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {

    private final StatusRepository repository;
    private final ObjectMapper mapper;

    @Override
    public Status getByName(String name) {
        return mapper.convertValue(repository.findByName(name), Status.class);
    }
}
