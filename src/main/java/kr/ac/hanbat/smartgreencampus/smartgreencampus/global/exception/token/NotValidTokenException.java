package kr.ac.hanbat.smartgreencampus.smartgreencampus.global.exception.token;

import kr.ac.hanbat.smartgreencampus.smartgreencampus.global.exception.BusinessException;

public class NotValidTokenException extends BusinessException {

    private static final String MESSAGE = "유효하지 않은 토큰입니다.";

    public NotValidTokenException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}
