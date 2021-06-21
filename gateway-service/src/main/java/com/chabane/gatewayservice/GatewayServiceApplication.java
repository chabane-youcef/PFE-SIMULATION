package com.chabane.gatewayservice;

import com.chabane.gatewayservice.services.Products.ProductIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class GatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }

    @Bean
    public CustomFilter getFilters(){
        return new CustomFilter();
    }

    @LoadBalanced
    @Bean
    RestTemplate restTemplate(){return new RestTemplate();}

    @Autowired
    ProductIntegrationService productIntegrationService;

    @Autowired
    CategoryIntegrationService categoryIntegrationService;
}
