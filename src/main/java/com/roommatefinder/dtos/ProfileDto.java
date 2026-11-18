package com.roommatefinder.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {

    private Long id;
    private String firstName;
    private String lastName;
    private Short userAge;
    private String gender;
    private Boolean matchGender;
    private String city;
    private Boolean isStudent;
    private String school;
    private String collegeMajor;
    private Boolean sharedGroceries;
    private Boolean isPetFriendly;
    private Boolean delegatedChores;
    private Byte cleanlinessPreference;
    private Byte socialPreference;
    private Byte guestPreference;
}
