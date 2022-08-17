package com.example.demo.controllers;

import com.example.demo.*;
import com.example.demo.model.persistence.*;
import com.example.demo.model.persistence.repositories.*;
import org.junit.*;
import org.mockito.*;
import org.springframework.http.*;
import static org.junit.Assert.*;

import java.util.*;

public class ItemControllerTest {
    private ItemController itemController;

    private ItemRepository itemRepository = Mockito.mock(ItemRepository.class);

    @Before
    public void setUp () {
        itemController = new ItemController();
        TestUtils.injectObjects(itemController, "itemRepository", itemRepository);
    }

    @Test
    public void get_all_items () {
        ResponseEntity<List<Item>> items = itemController.getItems();
        assertNotNull(items);
        assertEquals(200, items.getStatusCodeValue());
    }

    @Test
    public void get_items_by_id () {
        Item itemMock = TestUtils.createItemTest();
        Mockito.when(itemRepository.findById(1l)).thenReturn(Optional.of(itemMock));
        ResponseEntity<Item> item = itemController.getItemById(1l);
        assertNotNull(item);
        assertEquals(200, item.getStatusCodeValue());
    }

    @Test
    public void get_items_by_name () {
        List<Item> itemMocks = new ArrayList<>();
        itemMocks.add(new Item());
        Mockito.when(itemRepository.findByName("Macbook")).thenReturn(itemMocks);
        ResponseEntity<List<Item>> items = itemController.getItemsByName("Macbook");
        assertNotNull(items);
        assertEquals(200, items.getStatusCodeValue());
        assertEquals(1, items.getBody().size());
    }
}
