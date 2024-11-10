package edu.icet.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "cart_item")
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JsonBackReference("user-cart")
    private UserEntity user;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    private BookEntity book;

    @Column(nullable = false)
    private int quantity;
}
