package com.example.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lolka
 */
@SpringBootApplication(scanBasePackages = "com.example.*.*")
//@EnableDiscoveryClient
public class CcubeeProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CcubeeProducerApplication.class, args);
        System.out.println("生产者启动了");
    }

}
