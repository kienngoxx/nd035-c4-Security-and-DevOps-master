package com.example.demo;

import com.example.demo.model.persistence.*;
import com.example.demo.model.requests.*;

import java.lang.reflect.*;
import java.math.*;

public class TestUtils {
    public static void injectObjects (Object target, String fieldName, Object objectToInject) {
        boolean wasPrivate = false;

        try {
            Field f = target.getClass().getDeclaredField(fieldName);

            if (!f.isAccessible()) {
                f.setAccessible(true);
                wasPrivate = true;
            }

            f.set(target, objectToInject);
            if (wasPrivate) {
                f.setAccessible(false);
            }

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static User createUserTest() {
        User user = new User();
        user.setId(1L);
        user.setUsername("kiennv12");
        user.setPassword("thichancut");
        return user;
    }

    public static Item createItemTest() {
        Item item = new Item();
        item.setId(1L);
        item.setPrice(new BigDecimal(1.2345));
        item.setName("sẽtoy");
        return item;
    }
}
