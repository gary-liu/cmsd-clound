package com.lxjy.consumers.controller.fegin;

import com.lxjy.common.feign.config.CustomFeignConfig;
import com.lxjy.consumers.controller.fegin.factory.UserFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "USER-SERVICE",fallbackFactory = UserFallbackFactory.class,configuration = CustomFeignConfig.class)
public interface UserTestClient {

    @GetMapping("/find/{id}")
    String findUserById(@PathVariable("id") Integer id);
}
