package org.spring.service;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String paymentInfo_OK(Integer id){
        return "线程池： " + "\t" + Thread.currentThread().getName() + "\t" + "paymentInfo_OK,id：" + "\t" + id;
    }


    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String paymentInfo_TimeOut(Integer id){
        try {
            TimeUnit.SECONDS.sleep(5);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "线程池： " + "\t" + Thread.currentThread().getName() + "\t" + "paymentInfo_TimeOut,id：" + "\t" + id;
    }

    public String paymentInfo_TimeOutHandler(Integer id){

        return "线程池： " + "\t" + Thread.currentThread().getName() + "\t" + id + "系统繁忙，请稍后重试";
    }

    @HystrixCommand(fallbackMethod = "payments_CircuitBreakerHandler",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")//失败次数达到多少次后跳闸
    })
    public String payments_CircuitBreaker(Integer id){
        if (id <= 0){
            throw new RuntimeException("id不能小于0");
        }
        return "线程池： " + "\t" + Thread.currentThread().getName() + "\t" + "paymentInfo_TimeOut,id：" + "\t" + id;
    }

    public String payments_CircuitBreakerHandler (@PathVariable("id") Integer id){
        return Thread.currentThread().getName() + "\t" + "系统异常，请稍后重试" + "\t" + "id:" + id;
    }
}
