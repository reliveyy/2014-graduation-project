package com.oplogo.kira.exception;

/**
 * Created by yy on 5/25/14.
 */
public class ApplicationInitializeException extends Exception {
    public ApplicationInitializeException() {
        super();
    }

    public ApplicationInitializeException(String message) {
        super(message);
    }

    public ApplicationInitializeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationInitializeException(Throwable cause) {
        super(cause);
    }
}
