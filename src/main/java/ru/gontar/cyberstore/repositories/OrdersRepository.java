package ru.gontar.cyberstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gontar.cyberstore.entity.Order;

public interface OrdersRepository extends JpaRepository<Order, Integer> {
}
