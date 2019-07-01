package com.leigq.www.shiro.web.exception;

/**
 * @author ：leigq
 * @date ：2019/7/1 09:26
 * @description：登录异常
 */
public class LoginException extends RuntimeException {
    public LoginException() {
        super();
    }

    public LoginException(String message) {
        super(message);
    }

    public LoginException(String message, Throwable cause) {
        super(message, cause);
    }
}
