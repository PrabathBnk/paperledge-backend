package edu.icet.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.dto.Book;
import edu.icet.dto.OrderDetail;
import edu.icet.entity.OrderDetailEntity;
import edu.icet.entity.OrderEntity;
import edu.icet.repository.OrderDetailRepository;
import edu.icet.service.BookService;
import edu.icet.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository repository;
    private final ObjectMapper mapper;
    private final BookService bookService;

    @Override
    public void save(OrderDetail orderDetail) {
        //Update Book quantity
        Book book = bookService.getBookById(orderDetail.getBook().getId());
        bookService.updateQuantity(orderDetail.getBook().getId(), book.getQuantity() - orderDetail.getQuantity());

        OrderDetailEntity orderDetailEntity = mapper.convertValue(orderDetail, OrderDetailEntity.class);
        orderDetailEntity.setOrder(mapper.convertValue(orderDetail.getOrder(), OrderEntity.class));
        repository.save(orderDetailEntity);
    }

    @Override
    public List<OrderDetail> getAllByOrderId(String id) {
        Iterable<OrderDetailEntity> orderDetailEntities = repository.findAllByOrderId(id);
        List<OrderDetail> orderDetailList = new ArrayList<>();

        orderDetailEntities.forEach(orderDetailEntity -> orderDetailList.add(mapper.convertValue(orderDetailEntity, OrderDetail.class)));

        return orderDetailList;
    }
}
