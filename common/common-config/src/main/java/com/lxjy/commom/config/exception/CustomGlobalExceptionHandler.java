package com.lxjy.commom.config.exception;


import com.lxjy.common.core.exception.CommonException;
import com.lxjy.common.core.modul.ResponseBean;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 全局异常处理
 *
 */
@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * 处理参数校验异常
     *
     * @param ex      ex
     * @param headers headers
     * @param status  status
     * @param request request
     * @return ResponseEntity
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        // 获取所有异常信息
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        ResponseBean<List<String>> responseBean = new ResponseBean<>(errors);
        responseBean.setStatus(status.value());
        return new ResponseEntity<>(responseBean, headers, status);
    }

    /**
     * 处理CommonException
     *
     * @param e e
     * @return ResponseEntity
     */
    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ResponseBean<String>> handleCommonException(Exception e) {
        ResponseBean<String> responseBean = new ResponseBean<>();
        responseBean.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        responseBean.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        responseBean.setMsg("服务器开小差了,请稍后再试");
        return new ResponseEntity<>(responseBean, HttpStatus.OK);
    }

    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<ResponseBean<String>> handleSecurityException(Exception e) {
        ResponseBean<String> responseBean = new ResponseBean<>();
        responseBean.setStatus(ResponseBean.TOKEN_EXCEPTION);
        responseBean.setCode(ResponseBean.TOKEN_EXCEPTION);
        responseBean.setMsg(e.getMessage());
        return new ResponseEntity<>(responseBean, HttpStatus.OK);
    }
}