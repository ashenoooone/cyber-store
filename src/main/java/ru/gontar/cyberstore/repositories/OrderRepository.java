package ru.gontar.cyberstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gontar.cyberstore.entity.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByOrderByOrderDateAsc();
}
