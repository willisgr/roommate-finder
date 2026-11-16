package com.roommatefinder.mappers;

import com.roommatefinder.dtos.ProfileDto;
import com.roommatefinder.entities.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    @Mapping(source = "id", target = "userId")
    ProfileDto toDto(Profile profile);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Profile toEntity(ProfileDto profile);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    void update(ProfileDto newProfile, @MappingTarget Profile oldProfile);
}
