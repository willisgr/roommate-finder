package com.roommatefinder.controllers;

import com.roommatefinder.dtos.FindMatchDto;
import com.roommatefinder.dtos.MatchResultDto;
import com.roommatefinder.dtos.ProfileDto;
import com.roommatefinder.services.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDto> getProfileById(@PathVariable Long id) {
        return ResponseEntity.ok(profileService.getProfileById(id));
    }

    @GetMapping
    public List<ProfileDto> getProfiles() {
        return profileService.getAllProfiles();
    }

    @PostMapping
    public ResponseEntity<ProfileDto> createProfile(@RequestBody ProfileDto data) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(profileService.createProfile(data));
    }

    @PutMapping
    public ResponseEntity<ProfileDto> updateProfile(@RequestBody ProfileDto data) {
        return ResponseEntity.ok(profileService.updateProfile(data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        profileService.deleteProfile(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/get-match")
    public ResponseEntity<MatchResultDto> getSingleMatch(FindMatchDto request) {
        return ResponseEntity.ok(
                profileService.getSingleMatch(request.getTargetUserId(), request.getCandidateUserId())
        );
    }

    @GetMapping("/{id}/get-all-matches")
    public ResponseEntity<List<MatchResultDto>> getAllMatches(@PathVariable Long id) {
        return ResponseEntity.ok(profileService.getAllMatches(id));
    }
}