package org.spring.service;

import org.spring.bean.AjaxResponse;
import org.springframework.web.bind.annotation.PathVariable;

public interface OrderService {
    public AjaxResponse getPaymentById(@PathVariable("id") Integer id);
}
