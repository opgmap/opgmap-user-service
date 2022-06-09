package ru.opgmap.opgmap_user_service.exception.model;

public class NotAllowedOperationException extends RuntimeException {

    public NotAllowedOperationException(String message) {
        super(message);
    }

}
