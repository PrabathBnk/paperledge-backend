package edu.icet.service;

import edu.icet.dto.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    void save(OrderDetail orderDetail);
    List<OrderDetail> getAllByOrderId(String id);
}
