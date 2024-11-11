package edu.icet.controller;

import edu.icet.dto.Order;
import edu.icet.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public void persist(@RequestBody Order order){
        service.save(order);
    }

    @GetMapping
    public Order get(@RequestParam("id") String id){
        return service.getByOrderId(id);
    }

    @GetMapping("/all/by-user-id")
    public List<Order> getAllByUserId(@RequestParam("id") String id){
        return service.getAllByUserId(id);
    }
}
