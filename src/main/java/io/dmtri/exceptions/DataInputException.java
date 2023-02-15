package io.dmtri.exceptions;

public class DataInputException extends Exception{
    public DataInputException(String message) {
        super(message);
    }

    public DataInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataInputException(Throwable cause) {
        super(cause);
    }

    public DataInputException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
