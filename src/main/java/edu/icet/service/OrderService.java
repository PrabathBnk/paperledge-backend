package edu.icet.service;

import edu.icet.dto.Order;

import java.util.List;

public interface OrderService {
    void save(Order order);
    List<Order> getAllByUserId(String id);
    Order getByOrderId(String id);
}
