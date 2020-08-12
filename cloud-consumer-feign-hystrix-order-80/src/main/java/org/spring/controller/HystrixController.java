package org.spring.controller;


import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.spring.service.PaymentHystrixService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@DefaultProperties(defaultFallback = "defaultGlobalHandler")
public class HystrixController {

    @Resource
    private PaymentHystrixService service;

    @GetMapping(value = "/hystrix/ok/{id}")
    public String PaymentInfo_OK(@PathVariable Integer id){
        return service.PaymentInfo_OK(id);
    }

    @GetMapping(value = "/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "PaymentInfo_TimeoutHandler")
    public String PaymentInfo_Timeout(@PathVariable Integer id){
        int i = 10/0;
        return service.PaymentInfo_Timeout(id);
    }

    public String PaymentInfo_TimeoutHandler(@PathVariable("id") Integer id){
        return "80系统繁忙，请稍后重试" + id;
    };

    public String defaultGlobalHandler(){
        return "默认的系统异常，请稍后重试";
    }

    @GetMapping(value = "/hystrix/circuitBreaker/{id}")
    public String Payments_CircuitBreaker(@PathVariable Integer id){
        return service.Payments_CircuitBreaker(id);
    }
}
