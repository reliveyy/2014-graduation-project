package com.oplogo.kira.exception;

/**
 * Created by yy on 5/23/14.
 */
public class ApplicationConfigurationException extends Exception {
    public ApplicationConfigurationException() {
        super();
    }

    public ApplicationConfigurationException(String message) {
        super(message);
    }

    public ApplicationConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationConfigurationException(Throwable cause) {
        super(cause);
    }
}
