package com.example.demo.controllers;

import com.example.demo.*;
import com.example.demo.model.persistence.*;
import com.example.demo.model.persistence.repositories.*;
import com.example.demo.model.requests.*;
import org.junit.*;
import org.junit.Test;
import org.mockito.*;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.*;
import static org.junit.Assert.*;

import java.util.*;

public class UserControllerTest {

    private UserController userController;

    private UserRepository userRepository = Mockito.mock(UserRepository.class);

    private CartRepository cartRepository = Mockito.mock(CartRepository.class);

    private BCryptPasswordEncoder bCryptPasswordEncoder = Mockito.mock(BCryptPasswordEncoder.class);

    @Before
    public void setUp() {
        userController = new UserController();
        TestUtils.injectObjects(userController, "userRepository", userRepository);
        TestUtils.injectObjects(userController, "cartRepository", cartRepository);
        TestUtils.injectObjects(userController, "bCryptPasswordEncoder", bCryptPasswordEncoder);
    }

    @Test
    public void create_user_successfully(){
        Mockito.when(bCryptPasswordEncoder.encode("thichancut")).thenReturn("anhetsachcut");
        User userMock = TestUtils.createUserTest();
        CreateUserRequest request = new CreateUserRequest();
        request.setUsername(userMock.getUsername());
        request.setPassword(userMock.getPassword());
        request.setConfirmPassword(userMock.getPassword());
        ResponseEntity<User> response = userController.createUser(request);
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        User user = response.getBody();
        assertNotNull(user);
        assertEquals(0, user.getId());
        assertEquals(userMock.getUsername(), user.getUsername());
        assertEquals("anhetsachcut", user.getPassword());
    }

    @Test
    public void find_by_user_id() {
        User userMock = TestUtils.createUserTest();
        Mockito.when(userRepository.findById(1l)).thenReturn(Optional.of(userMock));
        ResponseEntity<User> user = userController.findById(1l);
        assertEquals(200, user.getStatusCodeValue());
    }

    @Test
    public void test_find_user_by_username() {
        User userMock = TestUtils.createUserTest();
        Mockito.when(userRepository.findByUsername("kiennv12")).thenReturn(userMock);
        ResponseEntity<User> user = userController.findByUserName("kiennv12");
        assertEquals(200, user.getStatusCodeValue());
    }
}
