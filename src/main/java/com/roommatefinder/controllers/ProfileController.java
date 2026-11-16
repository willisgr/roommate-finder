package com.roommatefinder.controllers;

import com.roommatefinder.dtos.FindMatchDto;
import com.roommatefinder.dtos.MatchResultDto;
import com.roommatefinder.dtos.ProfileDto;
import com.roommatefinder.mappers.ProfileMapper;
import com.roommatefinder.repositories.ProfileRepository;
import com.roommatefinder.services.ProfileService;
import com.roommatefinder.services.WeightedMatchingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;
    private final ProfileService profileService;
    private final WeightedMatchingService matcher;

    public ProfileController(
            ProfileRepository profileRepository,
            ProfileMapper profileMapper,
            ProfileService profileService,
            WeightedMatchingService weightedMatchingService) {

        this.profileRepository = profileRepository;
        this.profileMapper = profileMapper;
        this.profileService = profileService;
        this.matcher = weightedMatchingService;

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDto> getProfileById(@PathVariable Long id) {
        return ResponseEntity.ok(profileService.getProfile(id));
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
        var target = profileRepository.findById(request.getTargetUserId()).orElse(null);
        var candidate = profileRepository.findById(request.getCandidateUserId()).orElse(null);
        if (target == null || candidate == null) return ResponseEntity.notFound().build();

        var result = new MatchResultDto(
                target.getId(),
                candidate.getId(),
                matcher.getUserMatch(target, candidate));
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}/get-all-matches")
    public ResponseEntity<Iterable<MatchResultDto>> getAllMatches(@PathVariable Long id) {
        var target = profileRepository.findById(id).orElse(null);
        if (target == null) return ResponseEntity.notFound().build();

        var matches = matcher.getUserMatches(target);
        var result = new ArrayList<MatchResultDto>();
        matches.forEach((candidateId, percent) -> {
            result.add(new MatchResultDto(id, candidateId, percent));
        });
        return ResponseEntity.ok(result);
    }

}
