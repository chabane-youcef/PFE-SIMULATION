package com.ntic.discovery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;

@SpringBootApplication
public class DiscoveryApplication {
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DiscoveryApplication.class, args);
	}

}
