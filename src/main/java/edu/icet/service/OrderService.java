package edu.icet.service;

import edu.icet.dto.Order;

import java.util.List;

public interface OrderService {
    void save(Order order);
    List<Order> getAllByUserId(String id);
    Order getByOrderId(String id);
    void updateStatus(String id, String status);
    void updateEstDate(String id, String estDate);
    void updateTrackingNumber(String id, int trackingNumber);
    List<Order> getAllByBookOwnerId(String id);
}
