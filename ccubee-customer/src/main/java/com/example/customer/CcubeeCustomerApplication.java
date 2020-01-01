package com.example.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lolka
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CcubeeCustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CcubeeCustomerApplication.class, args);
        System.out.println("customer 启动了");
    }

}
