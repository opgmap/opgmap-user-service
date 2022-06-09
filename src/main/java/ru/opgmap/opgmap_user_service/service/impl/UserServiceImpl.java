package ru.opgmap.opgmap_user_service.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.opgmap.opgmap_user_service.dto.UserDto;
import ru.opgmap.opgmap_user_service.exception.utils.ExceptionMessagesGenerator;
import ru.opgmap.opgmap_user_service.mapper.UserMapper;
import ru.opgmap.opgmap_user_service.model.User;
import ru.opgmap.opgmap_user_service.repository.UserRepository;
import ru.opgmap.opgmap_user_service.service.UserService;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final static String ENTITY_NAME = "USER";

    @Override
    public UserDto update(UUID id, UserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        ExceptionMessagesGenerator.generateNotFoundMessage(ENTITY_NAME, id)));

        user.setUsername(userDto.getUsername());
        user.setAddress(userDto.getAddress());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());

        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserDto get(UUID id) {
        return userMapper.toDto(userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        ExceptionMessagesGenerator.generateNotFoundMessage(ENTITY_NAME, id))));
    }

    @Override
    public UserDto save(UUID userId, UserDto userDto) {
        User user = User.builder()
                .isVip(false)
                .phone(userDto.getPhone())
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .address(userDto.getAddress())
                .build();

        if (userId != null) {
            user.setId(userId);
        }
        return userMapper.toDto(userRepository.save(user));
    }

}

