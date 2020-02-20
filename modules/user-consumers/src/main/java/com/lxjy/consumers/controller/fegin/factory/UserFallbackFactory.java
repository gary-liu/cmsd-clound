package com.lxjy.consumers.controller.fegin.factory;

import com.lxjy.consumers.controller.fegin.UserTestClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class UserFallbackFactory implements FallbackFactory<UserTestClient> {
    @Override
    public UserTestClient create(Throwable throwable) {
        System.out.println("-----------------");
        System.out.println(throwable.getMessage());
        return new UserTestClient() {

            @Override
            public String findUserById(Integer id) {
                return "大爷不伺候了";
            }
        };
    }
}
