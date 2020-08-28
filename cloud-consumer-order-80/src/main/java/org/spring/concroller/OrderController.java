package org.spring.concroller;

import lombok.extern.slf4j.Slf4j;
import org.spring.bean.AjaxResponse;
import org.spring.bean.Payment;
import org.spring.lb.LoadBalanced;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class OrderController {

    private static final String PAYMENT_URL = "http://CLOUD-PROVIDER-SERVICE";

    @Resource
    private RestTemplate restTemplate;
    @Resource
    private LoadBalanced loadBalanced;
    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/payments")
    public AjaxResponse<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL + "/v1/payments",payment, AjaxResponse.class);
    }

    @GetMapping("/payments/{id}")
    public AjaxResponse<Payment> getPaymentById(@PathVariable("id") Integer id) {
       return restTemplate.getForObject(PAYMENT_URL + "/v1/payments/" + id,AjaxResponse.class);
    }

    @GetMapping("/entity/{id}")
    public AjaxResponse<Payment> getResponseEntity(@PathVariable("id") Integer id) {
        ResponseEntity<AjaxResponse> entity = restTemplate.getForEntity(PAYMENT_URL + "/v1/payments/" + id, AjaxResponse.class);
        log.info("{}",entity.getStatusCode());
        log.info("=============================");
        log.info("{}",entity.getHeaders());
        return restTemplate.getForObject(PAYMENT_URL + "/v1/payments/" + id,AjaxResponse.class);
    }

    @GetMapping(value = "/payment/lb")
    public String getLB(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PROVIDER-SERVICE");
        if (instances == null || instances.size() <= 0){
            return "系统开小差，请稍后重试";
        }
        return restTemplate.getForObject(loadBalanced.instances(instances).getUri() + "/v1/payments/lb", String.class);
    }

    @GetMapping("/payments/zipkin")
    public AjaxResponse<Payment> getZipkin(){
        return restTemplate.getForObject(PAYMENT_URL + "/v1/payments/zipkin", AjaxResponse.class);
    }
}
