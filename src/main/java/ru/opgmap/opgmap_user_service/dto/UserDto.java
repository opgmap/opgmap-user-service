package ru.opgmap.opgmap_user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.opgmap.opgmap_user_service.model.Address;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private UUID id;

    private String username;

    private String email;

    private String phone;

    private Address address;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private boolean isVip;

}
