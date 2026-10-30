package com.roommatefinder.controllers;

import com.roommatefinder.dtos.ProfileDto;
import com.roommatefinder.mappers.ProfileMapper;
import com.roommatefinder.repositories.ProfileRepository;
import com.roommatefinder.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;
    private final UserRepository userRepository;

    public ProfileController(ProfileRepository profileRepository, ProfileMapper profileMapper, UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.profileMapper = profileMapper;
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDto> getProfileById(@PathVariable Long id) {
        var profile = profileRepository.findById(id).orElse(null);
        if (profile == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(profileMapper.toDto(profile));
    }

    // might want to use an abbreviated profile dto for this
    @GetMapping
    public Iterable<ProfileDto> getProfiles() {
        return profileRepository.findAll()
                .stream()
                .map(profileMapper::toDto)
                .toList();
    }

    @PostMapping
    public ResponseEntity<ProfileDto> createProfile(ProfileDto data) {
        var user = userRepository.findById(data.getUserId()).orElse(null);
        // cannot create profile if user doesn't exist
        if (user == null) return ResponseEntity.badRequest().build();
        // cannot create profile if user already has one
        if (profileRepository.findById(data.getUserId()).orElse(null) != null) {
            return ResponseEntity.badRequest().build();
        }

        var profile = profileMapper.toEntity(data);
        profile.setUser(user);
        profileRepository.save(profile);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(profileMapper.toDto(profile));
    }

    @PutMapping
    public ResponseEntity<ProfileDto> updateProfile(ProfileDto data) {
        var profile = profileRepository.findById(data.getUserId()).orElse(null);
        if (profile == null) return ResponseEntity.badRequest().build();

        profileMapper.update(data, profile);
        profileRepository.save(profile);
        return ResponseEntity.ok(profileMapper.toDto(profile));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProfileDto> deleteProfile(@PathVariable Long id) {
        var profile = profileRepository.findById(id).orElse(null);
        if (profile == null) return ResponseEntity.notFound().build();
        profileRepository.delete(profile);
        return ResponseEntity.noContent().build();
    }

    // need to add endpoints for profile matching service

}
