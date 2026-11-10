package com.roommatefinder.mappers;

import com.roommatefinder.dtos.CreateUserRequest;
import com.roommatefinder.dtos.UserDto;
import com.roommatefinder.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);
    User toEntity(CreateUserRequest request);
}
