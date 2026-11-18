package com.roommatefinder.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FriendshipDto {
    private Long id;
    private Long userId;
    private Long friendId;
    private String status;
}