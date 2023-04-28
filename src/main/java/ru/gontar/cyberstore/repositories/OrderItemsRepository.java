package ru.gontar.cyberstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gontar.cyberstore.entity.OrderItems;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems, Integer> {
    List<OrderItems> findOrderItemsOrderByOrderOrderDateBetweenAndProductId(Date from, Date after, int productId);

    List<OrderItems> findOrderItemsOrderByOrderOrderDateBetween(Date from, Date after);
}
