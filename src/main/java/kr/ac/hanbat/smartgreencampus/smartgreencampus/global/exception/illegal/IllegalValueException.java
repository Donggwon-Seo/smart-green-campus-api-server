package kr.ac.hanbat.smartgreencampus.smartgreencampus.global.exception;

public class IllegalValueException extends BusinessException {

    private static final String MESSAGE = "비밀번호가 일치하지 않습니다.";

    public IllegalValueException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}
