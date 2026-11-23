package com.roommatefinder.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdatePasswordRequest {
    private Long userId;
    private String oldPassword;
    private String newPassword;
}
