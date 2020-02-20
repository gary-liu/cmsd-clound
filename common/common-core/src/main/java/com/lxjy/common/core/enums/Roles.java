package com.lxjy.common.core.enums;


import lombok.Data;

/**
 * 角色枚举
 *

 */

public enum Roles {
    /**
     * 普通用户
     */
    ROLE_USER("ROLE_USER"),

    /**
     * 超级管理员
     */
    ROLE_ADMIN("ROLE_ADMIN");


    Roles(String s) {
    }


}
