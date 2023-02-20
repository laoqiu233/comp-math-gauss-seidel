package io.dmtri.exceptions;

public class LinearSystemSolvingException extends Exception {
    public LinearSystemSolvingException(String message) {
        super(message);
    }

    public LinearSystemSolvingException(String message, Throwable cause) {
        super(message, cause);
    }

    public LinearSystemSolvingException(Throwable cause) {
        super(cause);
    }

    public LinearSystemSolvingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
