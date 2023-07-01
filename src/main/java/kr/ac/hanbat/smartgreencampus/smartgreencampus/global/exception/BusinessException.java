package kr.ac.hanbat.smartgreencampus.smartgreencampus.global.exception;

import lombok.Getter;

@Getter
public abstract class BusinessException extends RuntimeException {

    public BusinessException(final String message) {
        super(message);
    }

    public abstract int getStatusCode();
}
