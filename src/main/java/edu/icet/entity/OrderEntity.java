package edu.icet.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
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
@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    private String id;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private LocalTime time;
    private int trackingNumber;
    @Column(name = "estimated_delivery_date")
    private LocalDate estimatedDeliveryDate;
    @Column(nullable = false, name = "net_total")
    private double netTotal;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "payment_method", nullable = false)
    private PaymentMethodEntity paymentMethod;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(nullable = false)
    private StatusEntity status;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(nullable = false)
    @JsonBackReference("order-user")
    private UserEntity user;

    @OneToMany(mappedBy = "order")
    @JsonManagedReference("orderDetail")
    List<OrderDetailEntity> orderDetails;
}
