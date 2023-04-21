package ru.gontar.cyberstore.controller;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gontar.cyberstore.entity.Order;
import ru.gontar.cyberstore.service.OrdersService;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrdersController {
    @Autowired
    private OrdersService ordersService;


    @GetMapping("/")
    private ResponseEntity<?> getOrders() {
        return new ResponseEntity<>(ordersService.getAllOrders(), HttpStatus.OK);
    }

    @PostMapping("/")
    @Transactional
    private ResponseEntity<?> createOrder(@RequestBody Order order) {
        Order savedOrder = ordersService.createOrder(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.OK);
    }
}
