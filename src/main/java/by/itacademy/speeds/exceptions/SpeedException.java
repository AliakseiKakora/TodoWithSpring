package by.itacademy.speeds.exceptions;

public class SpeedException extends Exception {
    public SpeedException() {
    }

    public SpeedException(String message) {
        super(message);
    }

    public SpeedException(String message, Throwable cause) {
        super(message, cause);
    }

    public SpeedException(Throwable cause) {
        super(cause);
    }

    public SpeedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}