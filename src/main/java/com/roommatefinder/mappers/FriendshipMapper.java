package com.roommatefinder.mappers;

import com.roommatefinder.dtos.CreateFriendshipDto;
import com.roommatefinder.dtos.FriendshipDto;
import com.roommatefinder.entities.Friendship;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FriendshipMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "friend.id", target = "friendId")
    FriendshipDto toDto(Friendship friendship);

    List<FriendshipDto> toDtoList(List<Friendship> friendships);

    @Mapping(target = "id", ignore = true)
    Friendship toEntity(CreateFriendshipDto friendship);
}