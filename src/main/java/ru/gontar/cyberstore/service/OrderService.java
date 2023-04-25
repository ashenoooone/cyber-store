package ru.gontar.cyberstore.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gontar.cyberstore.entity.Order;
import ru.gontar.cyberstore.repositories.OrderRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository repository;

    @Transactional
    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    @Transactional
    public Order createOrder(Order order) {
        order.setOrderStatus("поступил");
        return repository.save(order);
    }

    @Transactional
    public List<Order> getAllOrdersSortedByDate() {
        return repository.findByOrderByOrderDateAsc();
    }

    public Order updateOrderStatus(int id, String status) {
        Order ord = repository.findOrderById(id);
        ord.setOrderStatus(status);
        return ord;
    }
}
