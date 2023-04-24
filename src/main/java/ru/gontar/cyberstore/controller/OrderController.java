package ru.gontar.cyberstore.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gontar.cyberstore.entity.Order;
import ru.gontar.cyberstore.service.OrderService;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private OrderService ordersService;


    @GetMapping("/")
    private ResponseEntity<?> getOrders() {
        return new ResponseEntity<>(ordersService.getAllOrders(), HttpStatus.OK);
    }

    @PostMapping("/")
    private ResponseEntity<?> createOrder(@RequestBody Order order) {
        Order savedOrder = ordersService.createOrder(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.OK);
    }

    @PutMapping("/")
    private ResponseEntity<?> updateOrderStatus(@RequestParam(name = "id")
                                                Long id,
                                                @RequestParam(name = "status")
                                                String status) {
        Order updatedOrder = ordersService.updateOrderStatus(id, status);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }
}
