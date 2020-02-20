package com.lxjy.commom.config.auth;

import com.lxjy.common.core.enums.Roles;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckAuthor {

    /**
     * 判断用户角色
     * @return
     */
    Roles [] hasRole();
}
