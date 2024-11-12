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

    @PutMapping("/status")
    public void updateStatus(@RequestParam("id") String orderId, @RequestParam("status") String status){
        service.updateStatus(orderId, status);
    }

    @PutMapping("/est-date")
    public void updateEstDate(@RequestParam("id") String orderId, @RequestParam("est-date") String estDate){
        service.updateEstDate(orderId, estDate);
    }

    @PutMapping("/tracking")
    public void updateTrackingNum(@RequestParam("id") String orderId, @RequestParam("tracking") int trackingNumber){
        service.updateTrackingNumber(orderId, trackingNumber);
    }

    @GetMapping("/all/by-owner-user-id")
    public List<Order> getAllOwnerUserId(@RequestParam("id") String id){
        return service.getAllByBookOwnerId(id);
    }
}
