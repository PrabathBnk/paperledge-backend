package edu.icet.service;

import edu.icet.dto.PaymentMethod;

import java.util.List;

public interface PaymentMethodService {
    PaymentMethod getByName(String name);
    List<PaymentMethod> getAll();
}
