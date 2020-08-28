package org.spring.service.impl;

import org.spring.bean.AjaxResponse;
import org.spring.service.OpenFeignService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class OpenfeignFallbackService implements OpenFeignService {
    @Override
    public AjaxResponse getPaymentById(Integer id) {
        return new AjaxResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),"服务降级了 OpenfeignFallbackService");
    }
}
