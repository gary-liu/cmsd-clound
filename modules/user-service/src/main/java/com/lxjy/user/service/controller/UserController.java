package com.lxjy.user.service.controller;

import com.lxjy.commom.config.auth.Authority;
import com.lxjy.commom.config.auth.CheckAuthor;
import com.lxjy.commom.config.util.JwtOperator;
import com.lxjy.common.core.enums.Roles;
import com.lxjy.user.service.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

   @Autowired
    private JwtOperator jwtOperator;

    @GetMapping("/find/{id}")
    @CheckAuthor(hasRole = {Roles.ROLE_ADMIN})
    public String findUserById(@PathVariable Integer id){
       return userDao.findUserById(id).getName();
    }

    @GetMapping("/find1/{id}")
    @CheckAuthor(hasRole = {Roles.ROLE_USER})
    public String findUserById1(@PathVariable Integer id){
        return userDao.findUserById(id).getName();
    }

    @GetMapping("/auth")
    public String getAuthToken(){
        // 1 登录
        Map<String,Object> userMap =new HashMap<>();
        userMap.put("userId","1001");
        userMap.put("userName","admin");
        userMap.put("role","ROLE_ADMIN");
        return jwtOperator.generateToken(userMap);
    }

}
