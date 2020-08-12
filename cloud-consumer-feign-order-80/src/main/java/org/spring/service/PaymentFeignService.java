package org.spring.service;

import org.spring.bean.AjaxResponse;
import org.spring.bean.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "CLOUD-PROVIDER-SERVICE")
public interface PaymentFeignService {

    @GetMapping(value = "/v1/payments/{id}")
    public AjaxResponse<Payment> getPaymentById(@PathVariable("id") Integer id);

    @GetMapping("/v1/payments/timeout")
    public String paymentFeignTimeout();
}
