package org.spring.service;

import org.spring.bean.AjaxResponse;
import org.spring.service.impl.OpenfeignFallbackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "nacos-alibaba-payment", fallback = OpenfeignFallbackService.class)
public interface OpenFeignService {

    @GetMapping(value = "/payments/{id}")
    public AjaxResponse getPaymentById(@PathVariable("id") Integer id);
}
