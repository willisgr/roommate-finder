package com.roommatefinder.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FindMatchDto {
    private Long targetUserId;
    private Long candidateUserId;
}
