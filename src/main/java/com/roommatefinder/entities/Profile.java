package com.roommatefinder.entities;

import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    @Column(name = "user_id", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    private User user;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "user_age", columnDefinition = "tinyint UNSIGNED not null")
    private Short userAge;

    @Lob
    @Column(name = "gender", nullable = false)
    // maps to single char in db. M = Male, F = Female, N = Non-binary, O = Other
    private String gender;

    // true or false. true = only match with the same gender.
    @Column(name = "match_gender", nullable = false)
    private Boolean matchGender;

    @Column(name = "city", nullable = false, length = 100)
    private String city;

    @Column(name = "is_student", nullable = false)
    private Boolean isStudent;

    @Column(name = "school", length = 100)
    private String school;

    @Column(name = "college_major", length = 100)
    private String collegeMajor;

    // t or f. does every roommate chip in for shared grocery budget?
    @Column(name = "shared_groceries", nullable = false)
    private Boolean sharedGroceries;

    @Column(name = "is_pet_friendly", nullable = false)
    private Boolean isPetFriendly;

    // true or false. true means dividing up tasks between roommates.
    // false means roommates only do their own dishes, take our their own trash etc.
    @Column(name = "delegated_chores", nullable = false)
    private Boolean delegatedChores;

    // scale values 1 to 5. How important is cleanliness for roommates.
    @Column(name = "cleanliness_preference", nullable = false)
    private Byte cleanlinessPreference;

    // scale values 1 to 5. The amount of preferred social interaction between roommates.
    @Column(name = "social_preference", nullable = false)
    private Byte socialPreference;

    // scale values 1 to 3. 1 = no guests, 2 = inform ahead of time, 3 = guests anytime.
    @Column(name = "guest_preference", nullable = false)
    private Byte guestPreference;

}