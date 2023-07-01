package kr.ac.hanbat.smartgreencampus.smartgreencampus.global.exception.nullcheck;

import kr.ac.hanbat.smartgreencampus.smartgreencampus.global.exception.BusinessException;

public class NullSensingDataException extends BusinessException {

    private static final String MESSAGE = "존재하지 않는 데이터입니다.";

    public NullSensingDataException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
