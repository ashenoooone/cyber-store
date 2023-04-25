package ru.gontar.cyberstore.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.gontar.cyberstore.entity.Order;
import ru.gontar.cyberstore.entity.User;

import java.util.Date;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void clearDatabase() {
        orderRepository.deleteAll();
    }

    @Test
    void findByOrderByOrderDateAsc() {
        // given
        Order order1 = new Order();
        order1.setOrderDate(new Date(System.currentTimeMillis() - 1000));
        Order order2 = new Order();
        order2.setOrderDate(new Date(System.currentTimeMillis()));
        Order order3 = new Order();
        order3.setOrderDate(new Date(System.currentTimeMillis() - 2000));
        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);
        // when
        List<Order> orders = orderRepository.findByOrderByOrderDateAsc();
        // then
        Assertions.assertNotNull(orders);
        Assertions.assertEquals(3, orders.size());
        Assertions.assertEquals(orders.get(0), order3);
        Assertions.assertEquals(orders.get(1), order1);
        Assertions.assertEquals(orders.get(2), order2);
    }

    @Test
    void findOrderById() {
        // given
        User user = new User();
        user.setEmail("test@mail.rulol");
        userRepository.save(user);
        Order order = new Order();
        order.setUser(user); // используем persistentUser для сохранения заказа
        order.setOrderDate(new Date());
        orderRepository.save(order);
        // when
        Order selectedOrder = orderRepository.findOrderById(order.getId());
        // then
        Assertions.assertNotNull(selectedOrder);
        Assertions.assertEquals(order.getOrderDate(), selectedOrder.getOrderDate());
        Assertions.assertEquals("test@mail.rulol", order.getUser().getEmail());
    }

    @Test
    void insertTest() {
//        given
        Order order = new Order();
        order.setOrderDate(new Date());
        orderRepository.save(order);
//        when
        List<Order> orders = orderRepository.findAll();
//        then
        Assertions.assertNotNull(orders);
        Assertions.assertEquals(order.getOrderDate(), orders.get(0).getOrderDate());
    }

    @Test
    void deleteTest() {
        //        given
        Order order1 = new Order();
        order1.setOrderDate(new Date());
        orderRepository.save(order1);
        Order order2 = new Order();
        order2.setOrderDate(new Date());
        orderRepository.save(order2);
//        when
        orderRepository.delete(order1);
        List<Order> orders = orderRepository.findAll();
//        then
        Assertions.assertNotNull(orders);
        Assertions.assertEquals(order2.getOrderDate(), orders.get(0).getOrderDate());
        Assertions.assertEquals(1, orders.size());
    }

    @Test
    void updateTest() {
//        given
        Order order = new Order();
        order.setOrderDate(new Date());
        order.setOrderStatus("getted");
        orderRepository.save(order);
//        when
        List<Order> orders = orderRepository.findAll();
        orders.get(0).setOrderStatus("sent");
//        then
        Assertions.assertNotNull(orders);
        Assertions.assertEquals(order.getOrderDate(), orders.get(0).getOrderDate());
        Assertions.assertEquals("sent", orders.get(0).getOrderStatus());
    }
}