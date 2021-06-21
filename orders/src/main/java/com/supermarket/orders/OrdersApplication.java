package com.supermarket.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;

@SpringBootApplication
public class OrdersApplication {
	@Autowired
	private static Environment environment;
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(OrdersApplication.class, args);
	}

}
