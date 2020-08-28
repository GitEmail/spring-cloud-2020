package org.spring.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.spring.bean.AjaxResponse;
import org.spring.handler.CustomBlockHandler;
import org.spring.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CircleBreakerController {

    @Resource
    private OrderService service;

    /**
     * @SentinelResource(
     * value: 可以自定义属性名,但是这个属性名必须是全局唯一的
     * fallback: 服务降级.发生java运行时异常时,会调用fallback属性指定的方法,但是这个方法需要额外接收一个Throwable异常参数
     * fallbackClass: 指定一个类的字节码属性,当发生java运行时异常时,fallback属性会在fallbackClass指定的类里调用对应方法,但这个方法必须是静态的方法
     * blockHandler: 服务限流.当定义的value属性违背sentinel定义的规则时,请求不会走原调用路径,转而走blockHandler指定的方法.但是这个方法必须接收一个BlockException异常参数
     * blockHandlerClass: 指定一个类的字节码属性,当定义的value属性违背sentinel定义的规则时,blockHandler属性会在blockHandlerClass指定的类里调用对应的方法,但这个方法必须是静态方法并且要接收一个BlockException异常参数
     * )
     * 调用的兜底方法必须要接收BlockException类型的参数,否则方法无效直接报找不到错
     * @param id
     * @return
     */
    @GetMapping(value = "/payments/{id}")
//    @SentinelResource(value = "fallback")
//    @SentinelResource(value = "fallback", fallback = "fallbackHandler")
//    @SentinelResource(value = "fallback",blockHandler = "blockHandler")
    @SentinelResource(value = "fallback",
            fallbackClass = CustomBlockHandler.class, fallback = "fallbackHandler",
            blockHandlerClass = CustomBlockHandler.class, blockHandler = "blockHandler"
    )
    public AjaxResponse fallback(@PathVariable("id") Integer id){
        if (id<=0){
            throw new IllegalArgumentException("IllegalArgumentException: 非法输入,id不能小于0");
        }else if(id >= 4) {
            throw new NullPointerException("NullPointerException: 输入id没有对应记录,空指针异常");
        }
        return service.getPaymentById(id);
    }

}
