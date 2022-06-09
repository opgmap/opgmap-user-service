package ru.opgmap.opgmap_user_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.opgmap.opgmap_user_service.dto.UserDto;
import ru.opgmap.opgmap_user_service.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "vip", target = "isVip")
    User fromDto(UserDto userDto);

    @Mapping(source = "vip", target = "isVip")
    UserDto toDto(User user);

}
