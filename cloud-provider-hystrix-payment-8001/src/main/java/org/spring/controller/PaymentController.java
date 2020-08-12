package org.spring.controller;


import lombok.extern.slf4j.Slf4j;
import org.spring.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService service;
    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/payments/hystrix/ok/{id}")
    public String PaymentInfo_OK(@PathVariable("id") Integer id){
        String info_ok = service.paymentInfo_OK(id);
        log.info("result =============" + info_ok);
        return info_ok;
    }

    @GetMapping(value = "/payments/hystrix/timeout/{id}")
    public String PaymentInfo_Timeout(@PathVariable("id") Integer id){
        String timeOut = service.paymentInfo_TimeOut(id);
        log.info("result =============" + timeOut);
        return timeOut;
    }
    @GetMapping(value = "/payments/hystrix/circuitbreaker/{id}")
    public String Payments_CircuitBreaker(@PathVariable("id") Integer id){
        return service.payments_CircuitBreaker(id);
    }
}
