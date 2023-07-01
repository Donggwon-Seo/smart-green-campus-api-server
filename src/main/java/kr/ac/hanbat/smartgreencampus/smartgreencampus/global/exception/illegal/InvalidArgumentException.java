package kr.ac.hanbat.smartgreencampus.smartgreencampus.global.exception.illegal;

import kr.ac.hanbat.smartgreencampus.smartgreencampus.global.exception.BusinessException;

public class InvalidArgumentException extends BusinessException {

    private static final String MESSAGE = "잘못된 요청입니다.";

    public InvalidArgumentException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}
