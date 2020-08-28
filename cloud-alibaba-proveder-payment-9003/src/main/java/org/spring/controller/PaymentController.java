package org.spring.controller;

import org.spring.bean.AjaxResponse;
import org.spring.bean.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PaymentController {
    @Value(value = "${server.port}")
    public String serverPort;

    public static HashMap<Integer, Payment> map = new HashMap<>();
    static {
        map.put(1, new Payment(1, "11111111111111111111111111111111111111111111111"));
        map.put(2, new Payment(2, "22222222222222222222222222222222222222222222222"));
        map.put(3, new Payment(3, "33333333333333333333333333333333333333333333333"));
    }

    @GetMapping(value = "/payments/{id}")
    public AjaxResponse getPaymentById(@PathVariable("id") Integer id){
        Payment payment = map.get(id);
        AjaxResponse<Payment> ajaxResponse = new AjaxResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase() + serverPort, payment);
        return ajaxResponse;

    }
}
