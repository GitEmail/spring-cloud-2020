package org.spring.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT")
public interface PaymentHystrixService {

    @GetMapping(value = "/payments/hystrix/ok/{id}")
    String PaymentInfo_OK(@PathVariable("id") Integer id);

    @GetMapping(value = "/payments/hystrix/timeout/{id}")
    public String PaymentInfo_Timeout(@PathVariable("id") Integer id);

    @GetMapping(value = "/payments/hystrix/circuitbreaker/{id}")
    public String Payments_CircuitBreaker(@PathVariable("id") Integer id);
}
