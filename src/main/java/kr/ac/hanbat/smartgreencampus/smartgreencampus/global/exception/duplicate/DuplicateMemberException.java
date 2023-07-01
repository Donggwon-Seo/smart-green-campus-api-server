package kr.ac.hanbat.smartgreencampus.smartgreencampus.global.exception.duplicate;

import kr.ac.hanbat.smartgreencampus.smartgreencampus.global.exception.BusinessException;

public class DuplicateMemberException extends BusinessException {

    private static final String MESSAGE = "이미 존재하는 회원입니다.";

    public DuplicateMemberException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 409;
    }
}
