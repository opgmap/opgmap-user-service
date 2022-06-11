package ru.opgmap.opgmap_user_service.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Affordance;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.opgmap.opgmap_user_service.dto.UserDto;
import ru.opgmap.opgmap_user_service.exception.model.NotAllowedOperationException;
import ru.opgmap.opgmap_user_service.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import static ru.opgmap.opgmap_user_service.controller.hateoas.Relations.USER;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "security_auth")
@PreAuthorize("isAuthenticated()")
@RequestMapping("/api/v1/user/")
public class UserController {

    private final UserService userService;

    private final Class<UserController> userController = UserController.class;

    @PostMapping("/oauth")
    public EntityModel<UserDto> create(Principal principal, @RequestBody UserDto userDto) {
        UserDto responseUser = userService.save(UUID.fromString(principal.getName()), userDto);
        return EntityModel.of(responseUser, linkTo(methodOn(userController).create(principal, userDto)).withSelfRel(),
                linkTo(methodOn(userController).getById(responseUser.getId())).withRel(USER)
                        .andAffordances(singleUserAffordances(responseUser.getId())));
    }

    @GetMapping("{id}")
    public EntityModel<UserDto> getById(@PathVariable UUID id) {
        return EntityModel.of(userService.get(id),
                linkTo(methodOn(userController).getById(id)).withSelfRel()
                        .andAffordances(singleUserAffordances(id)));
    }

    @PutMapping("{id}")
    public EntityModel<UserDto> update(Principal principal, @RequestBody UserDto userDto, @PathVariable UUID id) {
        isAllowed(principal, id);

        return EntityModel.of(userService.update(id, userDto),
                linkTo(methodOn(userController).update(principal, userDto, id)).withSelfRel()
                        .andAffordances(singleUserAffordances(id)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(Principal principal, @PathVariable UUID id) {
        isAllowed(principal, id);

        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private void isAllowed(Principal principal, UUID subjectId) {
        UUID principalId = UUID.fromString(principal.getName());
        if (!principalId.equals(subjectId)) {
            throw new NotAllowedOperationException(
                    "Operation with subject " + subjectId.toString() + " not allowed for user " + principalId
            );
        }
    }

    private List<Affordance> singleUserAffordances(UUID id) {
        return List.of(afford(methodOn(userController).getById(id)),
                afford(methodOn(userController).update(null, null, id)));
    }

}
