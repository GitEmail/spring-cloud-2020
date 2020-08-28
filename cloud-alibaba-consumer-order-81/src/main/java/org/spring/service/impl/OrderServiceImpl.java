package org.spring.service.impl;

import org.spring.bean.AjaxResponse;
import org.spring.service.OrderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private RestTemplate template;

    @Value(value = "${service.url}")
    private String serviceUrl;

    @Override
    public AjaxResponse getPaymentById(Integer id) {
        return template.getForObject(serviceUrl + "/payments/" + id,AjaxResponse.class);
    }
}
