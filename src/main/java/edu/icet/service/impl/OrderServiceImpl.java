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
import java.util.Optional;

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
            orderDetail.getOrder().setUser(order.getUser());
            orderDetailService.save(orderDetail);
        });
        order.setOrderDetails(null);
    }

    @Override
    public List<Order> getAllByUserId(String id) {
        return entityIterableToDtoList(repository.findAllByUserId(id));
    }

    @Override
    public Order getByOrderId(String id) {
        return mapper.convertValue(repository.findById(id).orElse(null), Order.class);
    }

    @Override
    public void updateStatus(String id, String status) {
        Optional<OrderEntity> orderEntity = repository.findById(id);
        if (orderEntity.isPresent()) {
            orderEntity.get().setStatus(mapper.convertValue(statusService.getByName(status), StatusEntity.class));
            repository.save(orderEntity.get());
        }
    }

    @Override
    public void updateEstDate(String id, String estDate) {
        Optional<OrderEntity> orderEntity = repository.findById(id);
        if (orderEntity.isPresent()) {
            orderEntity.get().setEstimatedDeliveryDate(LocalDate.parse(estDate));
            repository.save(orderEntity.get());
        }
    }

    @Override
    public void updateTrackingNumber(String id, int trackingNumber) {
        Optional<OrderEntity> orderEntity = repository.findById(id);
        if (orderEntity.isPresent()) {
            orderEntity.get().setTrackingNumber(trackingNumber);
            repository.save(orderEntity.get());
        }
    }

    @Override
    public List<Order> getAllByBookOwnerId(String id) {
        return entityIterableToDtoList(repository.findAllByBookOwnerUserId(id));
    }

    private List<Order> entityIterableToDtoList(Iterable<OrderEntity> orderEntityIterable){
        List<Order> orderList = new ArrayList<>();

        orderEntityIterable.forEach(orderEntity -> {
            Order order = mapper.convertValue(orderEntity, Order.class);
            order.setPaymentMethod(mapper.convertValue(orderEntity.getPaymentMethod(), PaymentMethod.class));
            order.setStatus(mapper.convertValue(orderEntity.getStatus(), Status.class));
            order.setUser(mapper.convertValue(orderEntity.getUser(), User.class));
            order.setOrderDetails(orderDetailService.getAllByOrderId(order.getId()));
            order.getUser().setPassword(null);
            orderList.add(order);
        });

        return orderList;
    }
}
