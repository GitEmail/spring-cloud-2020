package org.spring.controller;


import cn.hutool.core.lang.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payments/zk")
    public String paymentZK(){
        return "Spring-Cloud With Zookeeper:" + serverPort + "\t" + UUID.randomUUID().toString();
    }
}
