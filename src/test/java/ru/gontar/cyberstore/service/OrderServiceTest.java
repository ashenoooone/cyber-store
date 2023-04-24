package ru.gontar.cyberstore.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.gontar.cyberstore.repositories.OrderRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
class OrderServiceTest {
    @Autowired
    private OrderService orderService;
    @MockBean
    private OrderRepository orderRepository;

    @Test
    void getAllOrders() {
    }

    @Test
    void createOrder() {
    }

    @Test
    void getAllOrdersSortedByDate() {
    }

    @Test
    void updateOrderStatus() {
    }
}