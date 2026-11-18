package com.roommatefinder.mappers;

import com.roommatefinder.dtos.CreateUserRequest;
import com.roommatefinder.dtos.UserDto;
import com.roommatefinder.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "outgoingMessages", ignore = true)
    @Mapping(target = "incomingMessages", ignore = true)
    User toEntity(CreateUserRequest request);
}