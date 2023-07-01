package kr.ac.hanbat.smartgreencampus.smartgreencampus.global.exception.nullcheck;

import kr.ac.hanbat.smartgreencampus.smartgreencampus.global.exception.BusinessException;

public class NullMemberException extends BusinessException {

    private static final String MESSAGE = "존재하지 않는 회원입니다.";

    public NullMemberException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
