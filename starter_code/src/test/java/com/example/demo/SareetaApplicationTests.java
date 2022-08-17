package com.example.demo;

import com.example.demo.controllers.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest
@RunWith(Suite.class)
@Suite.SuiteClasses({CartControllerTest.class, UserControllerTest.class,
				ItemControllerTest.class, OrderControllerTest.class})
public class SareetaApplicationTests {

	@Test
	public void contextLoads() {
	}

}
