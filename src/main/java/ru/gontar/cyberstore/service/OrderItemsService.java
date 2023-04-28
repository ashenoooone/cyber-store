package ru.gontar.cyberstore.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gontar.cyberstore.entity.OrderItems;
import ru.gontar.cyberstore.repositories.OrderItemsRepository;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderItemsService {
    private OrderItemsRepository orderItemsRepository;

    public List<OrderItems> getAllProducts() {
        return orderItemsRepository.findAll();
    }

    public List<OrderItems> getAllOrderItemsByDateBetweenAndProductId(Date from, Date to, int id) {
        return orderItemsRepository.findOrderItemsOrderByOrderOrderDateBetweenAndProductId(from, to, id);
    }

    public List<OrderItems> getAllOrderItemsByDateBetween(Date from, Date to) {
        return orderItemsRepository.findOrderItemsOrderByOrderOrderDateBetween(from, to);
    }

}
