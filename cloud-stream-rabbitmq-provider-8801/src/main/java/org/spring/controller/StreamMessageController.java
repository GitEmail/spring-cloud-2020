package org.spring.controller;

import org.spring.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class StreamMessageController {

    @Resource
    private IMessageProvider message;
    @GetMapping(value = "/send/message")

    public String sendMessage(){
        return message.send();
    }
}
