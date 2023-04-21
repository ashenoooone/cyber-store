package ru.gontar.cyberstore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_items")
@Setter
@Getter
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_id")
    private int product_id;
    @Column(name = "price")
    private int price;
    @Column(name = "quantity")
    private int quantity;
}