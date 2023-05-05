package kr.ac.hanbat.smartgreencampus.smartgreencampus.global.exception.nullcheck;

public class NullSensingDataException extends RuntimeException {
    public NullSensingDataException() {
    }

    public NullSensingDataException(String message) {
        super(message);
    }

    public NullSensingDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullSensingDataException(Throwable cause) {
        super(cause);
    }

    public NullSensingDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
