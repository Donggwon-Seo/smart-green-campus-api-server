package kr.ac.hanbat.smartgreencampus.smartgreencampus.global.exception;

import jakarta.servlet.http.HttpServletRequest;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.global.exception.illegal.InvalidArgumentException;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.global.slack.SlackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    private final TaskExecutor executor;
    private final SlackService slackService;

    @Autowired
    public GlobalExceptionHandler(@Qualifier("taskExecutor") final TaskExecutor executor, final SlackService slackService) {
        this.executor = executor;
        this.slackService = slackService;
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> invalidRequestHandler(HttpServletRequest request) {

        ErrorResponse response = ErrorResponse.builder()
                .code("400")
                .message("잘못된 요청입니다.")
                .build();

        createLogAndSendAsync(new InvalidArgumentException(), request);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> businessException(final BusinessException exception, HttpServletRequest request) {

        int statusCode = exception.getStatusCode();

        ErrorResponse body = ErrorResponse.builder()
                .code(String.valueOf(statusCode))
                .message(exception.getMessage())
                .build();

        createLogAndSendAsync(exception, request);

        ResponseEntity<ErrorResponse> response = ResponseEntity.status(statusCode).body(body);
        return response;
    }

    private void createLogAndSendAsync(final Exception exception, final HttpServletRequest request) {

        log.error("Exception:{}, {}, {} \n",
                exception.toString(),
                request.getRequestURI(),
                request.getMethod()
        );

        executor.execute(() -> slackService.sendErrorLog(exception, request));
    }
}
