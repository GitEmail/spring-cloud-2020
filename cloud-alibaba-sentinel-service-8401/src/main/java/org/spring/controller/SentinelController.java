package org.spring.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SentinelController {

    @GetMapping(value = "/testA")
    public String testA(){
        return "~~~~~~~~~~~~~~~~~~~testA";
    }

    @GetMapping(value = "/testB")
    public String testB(){
        return "~~~~~~~~~~~~~~~~~~~testB";
    }

    @GetMapping(value = "/testC")
    @SentinelResource(value = "testC", blockHandler = "hotKet")//value监控的热点资源名(sentinel限流需要引用到),blockHandler兜底的方法,限流调用的方法
    public String testC(@RequestParam(value = "p1", required = false) String p1,
                        @RequestParam(value = "p2", required = false) String p2){
        return Thread.currentThread().getName() + "~~~~~~~~~~~~~~~~~~~testC" + p1;
    }

    public String hotKet(String p1, String p2, BlockException e){
        return Thread.currentThread().getName() + "~~~~~~~~~~~~~~~~~~~热点限流C";
    }
}
