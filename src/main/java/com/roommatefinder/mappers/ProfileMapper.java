package com.roommatefinder.mappers;

import com.roommatefinder.dtos.ProfileDto;
import com.roommatefinder.entities.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    @Mapping(source = "id", target = "userId")
    @Mapping(source = "userAge", target = "age")
    @Mapping(source = "school", target = "schoolName")
    @Mapping(source = "collegeMajor", target = "major")
    ProfileDto toDto(Profile profile);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "matchGender", constant = "false")
    @Mapping(source = "age", target = "userAge")
    @Mapping(source = "schoolName", target = "school")
    @Mapping(source = "major", target = "collegeMajor")
    Profile toEntity(ProfileDto profile);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "matchGender", ignore = true)
    @Mapping(source = "age", target = "userAge")
    @Mapping(source = "schoolName", target = "school")
    @Mapping(source = "major", target = "collegeMajor")
    void update(ProfileDto newProfile, @MappingTarget Profile oldProfile);
}