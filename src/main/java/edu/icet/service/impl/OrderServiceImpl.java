package edu.icet.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.dto.*;
import edu.icet.entity.*;
import edu.icet.repository.OrderRepository;
import edu.icet.service.*;
import edu.icet.util.GenerateIdUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final ObjectMapper mapper;
    private final OrderDetailService orderDetailService;
    private final PaymentMethodService paymentMethodService;
    private final StatusService statusService;
    private final UserService userService;

    @Transactional
    @Override
    public void save(Order order) {
        order.setId(GenerateIdUtil.generateId("PLOR" + LocalDate.now().getYear(), 4, repository.findTopId().orElse("0000")));

        List<OrderDetail> orderDetails = order.getOrderDetails();
        order.setOrderDetails(null);

        OrderEntity orderEntity = mapper.convertValue(order, OrderEntity.class);

        orderEntity.setDate(LocalDate.now());
        orderEntity.setTime(LocalTime.now());
        orderEntity.setPaymentMethod(mapper.convertValue(paymentMethodService.getByName(order.getPaymentMethod().getName()), PaymentMethodEntity.class));
        orderEntity.setStatus(mapper.convertValue(statusService.getByName(order.getStatus().getName()), StatusEntity.class));
        orderEntity.setUser(mapper.convertValue(userService.getUserById(order.getUser().getId()), UserEntity.class));

        repository.save(orderEntity);

        //Save Order details
        orderDetails.forEach(orderDetail -> {
            orderDetail.setOrder(mapper.convertValue(orderEntity, Order.class));
            orderDetailService.save(orderDetail);
        });
        order.setOrderDetails(null);
    }

    @Override
    public List<Order> getAllByUserId(String id) {
        Iterable<OrderEntity> orderEntityIterable = repository.findAllByUserId(id);
        List<Order> orderList = new ArrayList<>();

        orderEntityIterable.forEach(orderEntity -> {
            Order order = mapper.convertValue(orderEntity, Order.class);
            order.setPaymentMethod(mapper.convertValue(orderEntity.getPaymentMethod(), PaymentMethod.class));
            order.setStatus(mapper.convertValue(orderEntity.getStatus(), Status.class));
            order.setUser(mapper.convertValue(orderEntity.getUser(), User.class));
            order.setOrderDetails(orderDetailService.getAllByOrderId(order.getId()));
            order.setUser(null);
            orderList.add(order);
        });

        return orderList;
    }

    @Override
    public Order getByOrderId(String id) {
        return mapper.convertValue(repository.findById(id).orElse(null), Order.class);
    }
}
