package org.spring.controller;


import lombok.extern.slf4j.Slf4j;
import org.spring.bean.AjaxResponse;
import org.spring.bean.Payment;
import org.spring.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/v1")
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private Integer port;
    @Resource
    private PaymentService service;

    @GetMapping("/payments/{id}")
    public AjaxResponse<Payment> getById(@PathVariable("id") Integer id){
        Payment payment = service.getPaymentById(id);
        if (payment == null){
            return new AjaxResponse<>(HttpStatus.OK.value(), "查询没有对应的记录");
        }
        return new AjaxResponse<>(HttpStatus.OK.value(), "查询成功 port:" + port, payment);
    }

    @PostMapping("/payments")
    public AjaxResponse<Payment> create(@RequestBody Payment payment){
        int i = service.create(payment);
        if (i < 0){
            return new AjaxResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "创建错误,服务器没有添加对应记录");
        }
        return new AjaxResponse<>(HttpStatus.OK.value(), "创建时间成功", payment);
    }

    @GetMapping("/payments/lb")
    public String getLB(){
        return "响应端口：" + port;
    }
}
