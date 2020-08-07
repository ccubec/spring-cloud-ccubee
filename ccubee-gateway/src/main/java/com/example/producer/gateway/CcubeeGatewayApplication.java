package com.example.producer.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lolka
 */
@SpringBootApplication
public class CcubeeGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CcubeeGatewayApplication.class, args);
        System.out.println("网关启动了");
    }

}
