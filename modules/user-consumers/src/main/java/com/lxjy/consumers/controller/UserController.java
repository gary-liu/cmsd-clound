package com.lxjy.consumers.controller;


import com.lxjy.commom.config.auth.Authority;
import com.lxjy.consumers.controller.fegin.UserTestClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {

    @Autowired
    private UserTestClient userTestClient;

    @GetMapping("/test")
    @Authority
    @HystrixCommand(fallbackMethod = "fallbackMethod",
    threadPoolKey = "test",threadPoolProperties = {
            @HystrixProperty(name = "coreSize",value = "2"),
            @HystrixProperty(name = "maxQueueSize",value = "2"),
    })
    public String getaaaa(){
        Integer id = 1;
      return userTestClient.findUserById(id);
    }

    public String fallbackMethod(){
        return "降级信息";
    }
}
