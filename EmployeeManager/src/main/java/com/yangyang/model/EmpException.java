package com.yangyang.model;

/**
 * Created by syy on 2016/6/13.
 */
public class EmpException extends RuntimeException {
    public EmpException() {
        super();
    }

    public EmpException(String message) {
        super(message);
    }

    public EmpException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmpException(Throwable cause) {
        super(cause);
    }

    protected EmpException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
