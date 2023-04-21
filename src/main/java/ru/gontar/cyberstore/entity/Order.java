package ru.gontar.cyberstore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private int user_id;
    @Column
    private String order_status;
    @Column
    private String method_of_payment;
    @Column
    private Date order_date;
}
