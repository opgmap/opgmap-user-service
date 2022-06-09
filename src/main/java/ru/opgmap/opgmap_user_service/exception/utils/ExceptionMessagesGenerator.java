package ru.opgmap.opgmap_user_service.exception.utils;

import lombok.NoArgsConstructor;

import java.util.UUID;

public class ExceptionMessagesGenerator {

    public static String generateNotFoundMessage(String entity, UUID id) {
        return entity + " with id " + id + " not found";
    }

}

