package com.example.discorvey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lolka
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CcubeeDiscoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(CcubeeDiscoveryApplication.class, args);
        System.out.println("注册中心 启动了");
    }

}
