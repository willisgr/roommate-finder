package com.roommatefinder.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProfileDto {
    private Long userId;
    private String firstName;
    private String lastName;
    private Short age;
    private String gender;
    private String city;
    private Boolean isStudent;
    private String schoolName;
    private String major;
    private Boolean sharedGroceries;
    private Boolean isPetFriendly;
    private Boolean delegatedChores;
    private Byte cleanlinessPreference;
    private Byte socialPreference;
    private Byte guestPreference;
}
