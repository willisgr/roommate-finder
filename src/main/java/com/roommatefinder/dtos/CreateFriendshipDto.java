package com.roommatefinder.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateFriendshipDto {
    private Long userId;
    private Long friendId;
    private String status = "PENDING";
}
