package org.spring.service.impl;


import org.spring.bean.Payment;
import org.spring.mapper.PaymentMapper;
import org.spring.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentMapper mapper;

    @Override
    public Payment getPaymentById(Integer id) {
        return mapper.getPaymentById(id);
    }

    @Override
    public int create(Payment payment) {
        return mapper.create(payment);
    }
}
