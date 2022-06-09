package ru.opgmap.opgmap_user_service.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.opgmap.opgmap_user_service.dto.UserDto;
import ru.opgmap.opgmap_user_service.exception.model.NotAllowedOperationException;
import ru.opgmap.opgmap_user_service.service.UserService;

import java.security.Principal;
import java.util.UUID;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "security_auth")
@PreAuthorize("isAuthenticated()")
@RequestMapping("/api/v1/user/")
public class UserController {

    private final UserService userService;

    @PostMapping("/oauth")
    public UserDto create(Principal principal, @RequestBody UserDto userDto) {
        return userService.save(UUID.fromString(principal.getName()), userDto);
    }

    @GetMapping("{id}")
    public UserDto getById(@PathVariable UUID id) {
        return userService.get(id);
    }

    @PutMapping("{id}")
    public UserDto update(Principal principal, @RequestBody UserDto userDto, @PathVariable UUID id) {
        isAllowed(principal, id);

        return userService.update(id, userDto);
    }

    private void isAllowed(Principal principal, UUID subjectId) {
        UUID principalId = UUID.fromString(principal.getName());
        if (!principalId.equals(subjectId)) {
            throw new NotAllowedOperationException(
                    "Operation with subject " + subjectId.toString() + " not allowed for user " + principalId
            );
        }
    }

}
