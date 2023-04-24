package ru.gontar.cyberstore.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.gontar.cyberstore.entity.Order;

import java.util.Date;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    void findByOrderByOrderDateAsc() {
    }

    @Test
    void findOrderById() {
//        given
        Order order = new Order();
        order.setOrderDate(new Date());
        entityManager.persist(order);
        entityManager.flush();
//        when
        Order selectedOrder = orderRepository.findOrderById(order.getId());
//        then
        Assertions.assertNotNull(selectedOrder);
        Assertions.assertEquals(order.getOrderDate(), selectedOrder.getOrderDate());
    }
}