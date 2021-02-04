package me.yuxiaoyao.soffice.server.exception;

/**
 * @author kerryzhang on 2021/2/1
 */

public class OfficeStateException extends RuntimeException {
    public OfficeStateException() {
    }

    public OfficeStateException(String message) {
        super(message);
    }

    public OfficeStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public OfficeStateException(Throwable cause) {
        super(cause);
    }

    public OfficeStateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
