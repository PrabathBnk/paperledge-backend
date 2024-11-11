package edu.icet.service;

import edu.icet.dto.PaymentMethod;

public interface PaymentMethodService {
    PaymentMethod getByName(String name);
}
