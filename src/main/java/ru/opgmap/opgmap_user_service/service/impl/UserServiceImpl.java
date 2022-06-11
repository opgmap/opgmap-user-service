package ru.opgmap.opgmap_user_service.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.opgmap.opgmap_user_service.dto.UserDto;
import ru.opgmap.opgmap_user_service.exception.model.UserAlreadyExistAuthenticationException;
import ru.opgmap.opgmap_user_service.exception.utils.ExceptionMessagesGenerator;
import ru.opgmap.opgmap_user_service.mapper.UserMapper;
import ru.opgmap.opgmap_user_service.model.User;
import ru.opgmap.opgmap_user_service.model.UserStatus;
import ru.opgmap.opgmap_user_service.repository.UserRepository;
import ru.opgmap.opgmap_user_service.service.UserService;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;
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
        user.setUpdatedAt(LocalDateTime.now());

        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserDto get(UUID id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty() || user.get().getStatus() != UserStatus.ACTIVE) {
            throw new EntityNotFoundException(
                    ExceptionMessagesGenerator.generateNotFoundMessage(ENTITY_NAME, id)
            );
        } else {
            return userMapper.toDto(user.get());
        }

    }

    @Override
    public UserDto save(UUID userId, UserDto userDto) {
        userRepository.findById(userId).ifPresent(user -> {
            throw new UserAlreadyExistAuthenticationException("User with id [" + userId + "] already exist");
        });

        User user = User.builder()
                .isVip(false)
                .phone(userDto.getPhone())
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .address(userDto.getAddress())
                .status(UserStatus.ACTIVE)
                .createdAt(LocalDateTime.now())
                .build();

        if (userId != null) {
            user.setId(userId);
        }
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public void delete(UUID id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty() || user.get().getStatus() == UserStatus.DELETED) {
            throw new EntityNotFoundException(
                    ExceptionMessagesGenerator.generateNotFoundMessage(ENTITY_NAME, id)
            );
        } else {
            User persistUser = user.get();
            persistUser.setStatus(UserStatus.DELETED);
            userRepository.save(user.get());
        }
    }

}

