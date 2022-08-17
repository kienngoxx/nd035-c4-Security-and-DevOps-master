package com.example.demo.controllers;

import com.example.demo.*;
import com.example.demo.model.persistence.*;
import com.example.demo.model.persistence.repositories.*;
import com.example.demo.model.requests.*;
import org.junit.*;
import org.mockito.*;
import org.springframework.http.*;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito;

public class CartControllerTest {

    private CartController cartController;

    private ItemRepository itemRepository = Mockito.mock(ItemRepository.class);

    private UserRepository userRepository = Mockito.mock(UserRepository.class);

    private CartRepository cartRepository = Mockito.mock(CartRepository.class);

    @Before
    public void setUp () {
        cartController = new CartController();
        TestUtils.injectObjects(cartController, "userRepository", userRepository);
        TestUtils.injectObjects(cartController, "cartRepository", cartRepository);
        TestUtils.injectObjects(cartController, "itemRepository", itemRepository);
    }

    @Test
    public void add_to_card_successfully() {
        ModifyCartRequest modifyCartRequest = createModifyCartRequest();
        ResponseEntity<Cart> response = cartController.addTocart(modifyCartRequest);
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void remove_from_card_successfully() {
        ModifyCartRequest modifyCartRequest = createModifyCartRequest();
        ResponseEntity<Cart> response = cartController.removeFromcart(modifyCartRequest);
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
    }

    private ModifyCartRequest createModifyCartRequest() {
        User userMock = TestUtils.createUserTest();
        Mockito.when(userRepository.findByUsername(userMock.getUsername())).thenReturn(userMock);
        Mockito.when(cartRepository.save(any())).thenReturn(new Cart());
        Mockito.when(itemRepository.findById(any())).thenReturn(Optional.of(new Item()));
        ModifyCartRequest modifyCartRequest = new ModifyCartRequest();
        modifyCartRequest.setUsername(userMock.getUsername());
        return modifyCartRequest;
    }
}
