package com.example.demo.controllers;

import com.example.demo.*;
import com.example.demo.model.persistence.*;
import com.example.demo.model.persistence.repositories.*;
import org.junit.*;
import org.mockito.*;
import org.springframework.http.*;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

public class OrderControllerTest {
    private OrderController orderController;

    private OrderRepository orderRepository = Mockito.mock(OrderRepository.class);
    private UserRepository userRepository = Mockito.mock(UserRepository.class);

    @Before
    public void setUp () {
        orderController = new OrderController();
        TestUtils.injectObjects(orderController, "userRepository", userRepository);
        TestUtils.injectObjects(orderController, "orderRepository", orderRepository);
    }

    @Test
    public void test_submit_oder_by_username () {
        User userMock = TestUtils.createUserTest();
        Mockito.when(userRepository.findByUsername(userMock.getUsername())).thenReturn(userMock);
        Optional<UserOrder> order = Optional.of(new UserOrder());
        Mockito.when(orderRepository.findById(any())).thenReturn(order);
        Item item = TestUtils.createItemTest();
        List<Item> items = new ArrayList<>();
        items.add(item);
        Cart cart = new Cart();
        cart.setId(1L);
        cart.setItems(items);
        userMock.setCart(cart);
        Mockito.when(userRepository.findByUsername(userMock.getUsername())).thenReturn(userMock);
        verify(userMock);
    }

    @Test
    public void get_oder_for_user () {
        User userMock = TestUtils.createUserTest();
        Mockito.when(userRepository.findByUsername(userMock.getUsername())).thenReturn(userMock);
        Optional<UserOrder> userOrder = Optional.of(new UserOrder());
        Mockito.when(orderRepository.findById(any())).thenReturn(userOrder);
        verify(userMock);
    }

    private void verify(User user) {
        ResponseEntity<List<UserOrder>> responseOder = orderController.getOrdersForUser(user.getUsername());
        assertNotNull(responseOder);
        assertEquals(200, responseOder.getStatusCodeValue());
    }
}
