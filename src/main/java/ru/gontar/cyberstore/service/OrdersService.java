package ru.gontar.cyberstore.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.gontar.cyberstore.entity.Order;
import ru.gontar.cyberstore.repositories.OrdersRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class OrdersService {
    private OrdersRepository repository;

    @Transactional
    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    @Transactional
    public Order createOrder(Order order) {
        order.setOrder_status("поступил");
        return repository.save(order);
    }

    @Transactional
    public List<Order> getAllOrdersSortedByDate() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "order_date"));
    }
}
