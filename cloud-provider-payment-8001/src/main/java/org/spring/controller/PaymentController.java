package org.spring.controller;


import lombok.extern.slf4j.Slf4j;
import org.spring.bean.AjaxResponse;
import org.spring.bean.Payment;
import org.spring.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/v1")
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private Integer port;
    @Resource
    private PaymentService service;
    @Resource
    private DiscoveryClient discoveryClient;

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

    @GetMapping("/payments/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        services.stream().forEach(s -> {
            log.info(s);
        });

        discoveryClient.getInstances("CLOUD-PROVIDER-SERVICE")
                .stream()
                .forEach(server -> {
                    log.info(server.getServiceId() + "\t" + server.getHost() +"\t" + server.getPort() + "\t" + server.getUri());
                });
        return this.discoveryClient;
    }

    @GetMapping("/payments/lb")
    public String getLB(){
        return "响应端口：" + port;
    }

    @GetMapping("/payments/timeout")
    public String paymentFeignTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "响应端口：" + port;
    }
}
