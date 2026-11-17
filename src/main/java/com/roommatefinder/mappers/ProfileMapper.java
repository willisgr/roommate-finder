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

    @Mapping(source = "userId", target = "id")
    @Mapping(source = "age", target = "userAge")
    @Mapping(source = "schoolName", target = "school")
    @Mapping(source = "major", target = "collegeMajor")
    @Mapping(target = "user", ignore = true)
    Profile toEntity(ProfileDto profile);

    @Mapping(source = "age", target = "userAge")
    @Mapping(source = "schoolName", target = "school")
    @Mapping(source = "major", target = "collegeMajor")
    void update(ProfileDto newProfile, @MappingTarget Profile oldProfile);
}