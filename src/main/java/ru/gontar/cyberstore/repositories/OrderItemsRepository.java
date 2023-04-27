package ru.gontar.cyberstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gontar.cyberstore.entity.OrderItems;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Integer> {

}
