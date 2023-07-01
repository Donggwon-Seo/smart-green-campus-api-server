package kr.ac.hanbat.smartgreencampus.smartgreencampus.global.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorResponse {

    private final String code;
    private final String message;

    @Builder
    public ErrorResponse(final String code, final String message) {
        this.code = code;
        this.message = message;
    }
}
