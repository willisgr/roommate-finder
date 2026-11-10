package com.roommatefinder.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MatchResultDto {
    private Long targetUserId;
    private Long candidateUserId;
    private int matchPercent;
}
