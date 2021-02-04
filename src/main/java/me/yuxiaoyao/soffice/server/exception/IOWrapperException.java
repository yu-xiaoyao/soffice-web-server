package me.yuxiaoyao.soffice.server.exception;

/**
 * @author kerryzhang on 2021/2/3
 */

public class IOWrapperException extends RuntimeException {
    public IOWrapperException() {
    }

    public IOWrapperException(String message) {
        super(message);
    }

    public IOWrapperException(String message, Throwable cause) {
        super(message, cause);
    }

    public IOWrapperException(Throwable cause) {
        super(cause);
    }

    public IOWrapperException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
