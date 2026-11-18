package com.roommatefinder.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FriendshipDto {
    private Long id;
    private Long userId;
    private String username;
    private Long friendId;
    private String friendUsername;
    private String status;
}