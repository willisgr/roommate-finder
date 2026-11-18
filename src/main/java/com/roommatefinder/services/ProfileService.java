package com.roommatefinder.services;

import com.roommatefinder.dtos.MatchResultDto;
import com.roommatefinder.dtos.ProfileDto;
import com.roommatefinder.mappers.ProfileMapper;
import com.roommatefinder.repositories.ProfileRepository;
import com.roommatefinder.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final ProfileMapper profileMapper;
    private final WeightedMatchingService matchingService;

    @Transactional(readOnly = true)
    public ProfileDto getProfileById(Long id) {
        var profile = profileRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found"));
        return profileMapper.toDto(profile);
    }

    @Transactional(readOnly = true)
    public List<ProfileDto> getAllProfiles() {
        return profileRepository.findAll()
                .stream()
                .map(profileMapper::toDto)
                .toList();
    }

    @Transactional
    public ProfileDto createProfile(ProfileDto data) {
        if (data.getUserId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User ID is required");
        }

        var user = userRepository.findById(data.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User does not exist"));

        if (profileRepository.existsById(data.getUserId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Profile already exists for this user");
        }

        var profile = profileMapper.toEntity(data);
        profile.setUser(user);
        if (profile.getMatchGender() == null) {
            profile.setMatchGender(false);
        }

        var savedProfile = profileRepository.save(profile);
        return profileMapper.toDto(savedProfile);
    }

    @Transactional
    public ProfileDto updateProfile(ProfileDto data) {
        if (data.getUserId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User ID is required");
        }

        var profile = profileRepository.findById(data.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found"));

        profileMapper.update(data, profile);
        var updatedProfile = profileRepository.save(profile);
        return profileMapper.toDto(updatedProfile);
    }

    @Transactional
    public void deleteProfile(Long id) {
        var profile = profileRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found"));
        profileRepository.delete(profile);
    }

    @Transactional(readOnly = true)
    public MatchResultDto getSingleMatch(Long targetUserId, Long candidateUserId) {
        var target = profileRepository.findById(targetUserId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Target profile not found"));
        var candidate = profileRepository.findById(candidateUserId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidate profile not found"));

        int percent = matchingService.getUserMatch(target, candidate);
        return new MatchResultDto(target.getId(), candidate.getId(), percent);
    }

    @Transactional(readOnly = true)
    public List<MatchResultDto> getAllMatches(Long id) {
        var target = profileRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found"));

        var matches = matchingService.getUserMatches(target);
        var results = new ArrayList<MatchResultDto>();
        matches.forEach((candidateId, percent) ->
                results.add(new MatchResultDto(id, candidateId, percent)));
        return results;
    }
}