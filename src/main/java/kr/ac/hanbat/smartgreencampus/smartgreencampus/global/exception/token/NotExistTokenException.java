package kr.ac.hanbat.smartgreencampus.smartgreencampus.global.exception.token;

import kr.ac.hanbat.smartgreencampus.smartgreencampus.global.exception.BusinessException;

public class NotExistTokenException extends BusinessException {

    private static final String MESSAGE = "토큰이 존재하지 않습니다.";

    public NotExistTokenException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 401;
    }
}
