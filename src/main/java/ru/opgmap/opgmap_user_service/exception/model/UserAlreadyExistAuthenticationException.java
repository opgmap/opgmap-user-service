package ru.opgmap.opgmap_user_service.exception.model;

public class UserAlreadyExistAuthenticationException extends RuntimeException {

    public UserAlreadyExistAuthenticationException(String message) {
        super(message);
    }

}
