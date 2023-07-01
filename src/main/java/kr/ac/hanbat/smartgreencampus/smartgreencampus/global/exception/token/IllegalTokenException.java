package kr.ac.hanbat.smartgreencampus.smartgreencampus.global.exception.token;

import kr.ac.hanbat.smartgreencampus.smartgreencampus.global.exception.BusinessException;

public class IllegalTokenException extends BusinessException {

    private static final String MESSAGE = "이미 로그아웃된 토큰입니다.";

    public IllegalTokenException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 401;
    }
}
