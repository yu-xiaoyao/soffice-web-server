package me.yuxiaoyao.soffice.server.exception;

/**
 * @author kerryzhang on 2021/2/1
 */

public class FormatNotSupportException extends RuntimeException {
    public FormatNotSupportException() {
    }

    public FormatNotSupportException(String message) {
        super(message);
    }

    public FormatNotSupportException(String message, Throwable cause) {
        super(message, cause);
    }

    public FormatNotSupportException(Throwable cause) {
        super(cause);
    }

    public FormatNotSupportException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
