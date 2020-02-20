package com.lxjy.commom.config.aspect;


import com.google.gson.JsonObject;
import com.lxjy.commom.config.auth.CheckAuthor;
import com.lxjy.commom.config.util.JwtOperator;
import com.lxjy.common.core.enums.Roles;
import com.lxjy.common.core.modul.ResponseBean;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 鉴权授权 验证
 */
@Slf4j
@Aspect
@Component
public class AuthAspect {

    @Autowired
    private JwtOperator jwtOperator;

    /**
     * 检查校验token
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("@annotation(com.lxjy.commom.config.auth.Authority)")
    public Object checkToken(ProceedingJoinPoint point) throws Throwable {
        validationToken();
        return point.proceed();
    }

    /**
     * 授权检查
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("@annotation(com.lxjy.commom.config.auth.CheckAuthor)")
    public Object CheckAuthor(ProceedingJoinPoint point) throws Throwable {
        HttpServletRequest request = validationToken();
        String role = String.valueOf(request.getAttribute("role"));
        MethodSignature signature = (MethodSignature)point.getSignature();
        Method method = signature.getMethod();
        CheckAuthor annotation = method.getAnnotation(CheckAuthor.class);
        Roles[] roles = annotation.hasRole();
        boolean isValid = Arrays.stream(roles).anyMatch(r -> r.toString().equalsIgnoreCase(role));
        if(!isValid){
            throw new SecurityException("没有此操作权限");
        }
        return  point.proceed();
    }

    public HttpServletRequest validationToken() throws Throwable {
        ServletRequestAttributes attributes= (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String authorization = request.getHeader("authorization");
        Boolean isValid;
        try {
            isValid = jwtOperator.validateToken(authorization);
            if(!isValid){
                throw new SecurityException("token 为空");
            }
            Claims userInfo = jwtOperator.getClaimsFromToken(authorization);
            request.setAttribute("userId",userInfo.get("userId"));
            request.setAttribute("userName",userInfo.get("userName"));
            request.setAttribute("role",userInfo.get("role"));
        }catch (Exception e){
            throw new SecurityException("token 异常");
        }
            return request;
    }
}
