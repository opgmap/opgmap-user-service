package ru.opgmap.opgmap_user_service.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import ru.opgmap.opgmap_user_service.exception.model.NotAllowedOperationException;
import ru.opgmap.opgmap_user_service.exception.model.UserAlreadyExistAuthenticationException;
import ru.opgmap.opgmap_user_service.exception.utils.ErrorMessage;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handle(EntityNotFoundException exception, WebRequest request) {
        return buildMessage(exception, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(NotAllowedOperationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorMessage handle(NotAllowedOperationException exception, WebRequest request) {
        return buildMessage(exception, HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler(UserAlreadyExistAuthenticationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handle(UserAlreadyExistAuthenticationException exception, WebRequest request) {
        return buildMessage(exception, HttpStatus.BAD_REQUEST, request);
    }

    private ErrorMessage buildMessage(Throwable throwable, HttpStatus status, WebRequest request) {
        return ErrorMessage.builder()
                .path(((ServletWebRequest)request).getRequest().getRequestURI())
                .statusCode(status.value())
                .message(throwable.getMessage())
                .build();
    }

}
