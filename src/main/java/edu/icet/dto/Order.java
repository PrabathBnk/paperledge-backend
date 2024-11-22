package edu.icet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {
    private String id;
    private LocalDate date;
    private LocalTime time;
    private String trackingNumber;
    private LocalDate estimatedDeliveryDate;
    private double netTotal;
    private PaymentMethod paymentMethod;
    private Status status;
    private User user;
    private List<OrderDetail> orderDetails;
}
