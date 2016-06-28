package com.yangyang.model;

public class StrutsException extends RuntimeException{
    public StrutsException() {
        super();
    }

    public StrutsException(String message) {
        super(message);
    }

    public StrutsException(String message, Throwable cause) {
        super(message, cause);
    }

    public StrutsException(Throwable cause) {
        super(cause);
    }

    protected StrutsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
