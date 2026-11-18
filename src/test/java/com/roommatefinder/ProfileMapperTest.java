package com.roommatefinder;

import com.roommatefinder.dtos.ProfileDto;
import com.roommatefinder.entities.Profile;
import com.roommatefinder.mappers.ProfileMapper;
import com.roommatefinder.mappers.ProfileMapperImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProfileMapperTest {

    private final ProfileMapper mapper = new ProfileMapperImpl();

    @Test
    void shouldMapProfileToDto() {
        Profile profile = Profile.builder()
                .id(42L)
                .firstName("John")
                .lastName("Doe")
                .userAge((short) 25)
                .gender("M")
                .matchGender(true)
                .city("New York")
                .school("NYU")
                .collegeMajor("Computer Science")
                .build();

        ProfileDto dto = mapper.toDto(profile);

        assertEquals(42L, dto.getId());
        assertEquals((short) 25, dto.getUserAge());
        assertEquals("NYU", dto.getSchool());
        assertEquals("Computer Science", dto.getCollegeMajor());
        assertEquals(true, dto.getMatchGender());

        // sanity checks
        assertEquals("John", dto.getFirstName());
        assertEquals("Doe", dto.getLastName());
        assertEquals("M", dto.getGender());
        assertEquals("New York", dto.getCity());
    }
}