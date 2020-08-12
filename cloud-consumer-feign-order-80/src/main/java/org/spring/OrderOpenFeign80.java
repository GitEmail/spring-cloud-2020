package org.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrderOpenFeign80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderOpenFeign80.class, args);
    }
}
