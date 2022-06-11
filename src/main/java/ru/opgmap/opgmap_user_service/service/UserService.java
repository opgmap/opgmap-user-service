package ru.opgmap.opgmap_user_service.service;

import org.springframework.hateoas.EntityModel;
import ru.opgmap.opgmap_user_service.dto.UserDto;

import java.util.UUID;

public interface UserService {
    UserDto update(UUID id, UserDto userDto);

    UserDto get(UUID id);

    UserDto save(UUID userId, UserDto userDto);

    void delete(UUID id);

}
