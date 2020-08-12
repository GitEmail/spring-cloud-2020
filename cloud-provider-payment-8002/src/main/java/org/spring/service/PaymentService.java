package org.spring.service;


import org.spring.bean.Payment;

public interface PaymentService {

    Payment getPaymentById(Integer id);

    int create(Payment payment);
}
