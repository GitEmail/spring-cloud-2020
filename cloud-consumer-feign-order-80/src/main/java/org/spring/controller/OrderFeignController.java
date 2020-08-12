package org.spring.controller;


import org.spring.bean.AjaxResponse;
import org.spring.bean.Payment;
import org.spring.service.PaymentFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderFeignController {

    @Resource
    private PaymentFeignService feignService;

    @GetMapping("/payments/{id}")
    public AjaxResponse<Payment> getPaymentById(@PathVariable("id") Integer id) {
        return feignService.getPaymentById(id);
    }

    @GetMapping("/payments/timeout")
    public String paymentFeignTimeout(){
        return feignService.paymentFeignTimeout();
    }
}
