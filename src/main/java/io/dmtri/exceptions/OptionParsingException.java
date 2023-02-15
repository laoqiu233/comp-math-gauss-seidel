package io.dmtri.exceptions;

public class OptionParsingException extends Exception {
    public OptionParsingException(String message) {
        super(message);
    }

    public OptionParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public OptionParsingException(Throwable cause) {
        super(cause);
    }

    public OptionParsingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
