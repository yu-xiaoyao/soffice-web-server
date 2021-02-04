package me.yuxiaoyao.soffice.server.exception;

/**
 * @author kerryzhang on 2021/2/1
 */

public class ConvertFailedException extends RuntimeException {
    public ConvertFailedException() {
    }

    public ConvertFailedException(String message) {
        super(message);
    }

    public ConvertFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConvertFailedException(Throwable cause) {
        super(cause);
    }

    public ConvertFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
