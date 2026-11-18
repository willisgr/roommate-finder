package com.roommatefinder.mappers;

import com.roommatefinder.dtos.FriendshipDto;
import com.roommatefinder.entities.Friendship;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FriendshipMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "friend.id", target = "friendId")
    FriendshipDto toDto(Friendship friendship);
}