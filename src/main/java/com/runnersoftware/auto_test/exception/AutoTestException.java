package com.runnersoftware.auto_test.exception;


public class AutoTestException extends RuntimeException{
    public AutoTestException() {
    }

    public AutoTestException(String message) {
        super(message);
    }

    public AutoTestException(String message, Throwable cause) {
        super(message, cause);
    }

    public AutoTestException(Throwable cause) {
        super(cause);
    }

    public AutoTestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
