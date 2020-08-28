package org.spring.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.spring.bean.AjaxResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;

public class CustomBlockHandler {



    public static AjaxResponse fallbackHandler(@PathVariable Integer id, Throwable e){
        return new AjaxResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),"系统开小差,请稍后重试" + e.getMessage());
    }

    public static AjaxResponse blockHandler(@PathVariable Integer id, BlockException e){
        return new AjaxResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),"服务熔断");
    }
}
