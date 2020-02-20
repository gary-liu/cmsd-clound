package com.lxjy.common.core.exception;

public class SecurityException extends RuntimeException {

    public SecurityException() {

    }

    public SecurityException(String msg) {
        super(msg);
    }

    public SecurityException(Throwable throwable) {
        super(throwable);
    }

    public SecurityException(Throwable throwable, String msg) {
        super(throwable);
    }

}