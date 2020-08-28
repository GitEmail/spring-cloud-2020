package org.spring.service.controller;

import org.spring.bean.AjaxResponse;
import org.spring.bean.Payment;
import org.spring.service.OpenFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class PaymentController {
    @Resource
    private OpenFeignService service;

    @GetMapping(value = "/payments/{id}")
    public AjaxResponse<Payment> getPaymentById(@PathVariable("id") Integer id){
//        if (id <= 0){
//            throw new RuntimeException("错误的请求参数,id不能小于0");
//        }else if (id >= 4){
//            throw new RuntimeException("空指针异常,请求id:" + id + " 针对的是不存在的记录");
//        }
        return service.getPaymentById(id);
    }
}
