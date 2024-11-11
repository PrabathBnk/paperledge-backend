package edu.icet.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.dto.PaymentMethod;
import edu.icet.repository.PaymentMethodRepository;
import edu.icet.service.PaymentMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private final PaymentMethodRepository repository;
    private final ObjectMapper mapper;

    @Override
    public PaymentMethod getByName(String name) {
        return mapper.convertValue(repository.findByName(name).orElse(null), PaymentMethod.class);
    }
}
