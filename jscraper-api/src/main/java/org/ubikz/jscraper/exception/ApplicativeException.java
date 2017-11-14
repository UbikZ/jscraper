package org.ubikz.jscraper.exception;

public class ApplicativeException extends RuntimeException {

    public ApplicativeException() {
        super();
    }

    public ApplicativeException(String message) {
        super(message);
    }

    public ApplicativeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicativeException(Throwable cause) {
        super(cause);
    }

    protected ApplicativeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public static void uncheck(Throwable throwable) {
        if (throwable instanceof ApplicativeException) {
            throw (ApplicativeException) throwable;
        }
        throw new ApplicativeException(throwable);
    }
}
