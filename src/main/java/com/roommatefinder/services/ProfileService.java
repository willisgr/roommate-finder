package com.roommatefinder.services;

import com.roommatefinder.dtos.ProfileDto;
import com.roommatefinder.entities.Profile;
import com.roommatefinder.exceptions.ProfileAlreadyExistsException;
import com.roommatefinder.exceptions.ProfileNotFoundException;
import com.roommatefinder.exceptions.UserNotFoundException;
import com.roommatefinder.mappers.ProfileMapper;
import com.roommatefinder.repositories.ProfileRepository;
import com.roommatefinder.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Business logic for creating, reading, updating and deleting roommate profiles.
 * A profile shares its id with the user it belongs to.
 */
@Service
@AllArgsConstructor
@Slf4j
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final ProfileMapper profileMapper;

    /**
     * Gets the profile for a user.
     *
     * @param userId id of the user who owns the profile
     * @return the profile as a DTO
     * @throws ProfileNotFoundException if the user has no profile
     */
    public ProfileDto getProfile(Long userId) {
        return profileMapper.toDto(findProfile(userId));
    }

    /**
     * Creates a profile for an existing user.
     *
     * @param data profile fields, including the id of the user it belongs to
     * @return the saved profile as a DTO
     * @throws UserNotFoundException if the user does not exist
     * @throws ProfileAlreadyExistsException if the user already has a profile
     */
    @Transactional
    public ProfileDto createProfile(ProfileDto data) {
        Long userId = data.getUserId();
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        if (profileRepository.existsById(userId)) {
            throw new ProfileAlreadyExistsException(userId);
        }

        var profile = profileMapper.toEntity(data);
        profile.setUser(user);
        profileRepository.save(profile);
        log.info("Created profile for user id: {}", userId);
        return profileMapper.toDto(profile);
    }

    /**
     * Updates an existing profile with new values.
     *
     * @param data new profile fields, including the id of the user it belongs to
     * @return the updated profile as a DTO
     * @throws ProfileNotFoundException if the user has no profile
     */
    @Transactional
    public ProfileDto updateProfile(ProfileDto data) {
        var profile = findProfile(data.getUserId());
        profileMapper.update(data, profile);
        profileRepository.save(profile);
        log.info("Updated profile for user id: {}", data.getUserId());
        return profileMapper.toDto(profile);
    }

    /**
     * Deletes a user's profile.
     *
     * @param userId id of the user who owns the profile
     * @throws ProfileNotFoundException if the user has no profile
     */
    @Transactional
    public void deleteProfile(Long userId) {
        profileRepository.delete(findProfile(userId));
        log.info("Deleted profile for user id: {}", userId);
    }

    private Profile findProfile(Long userId) {
        return profileRepository.findById(userId)
                .orElseThrow(() -> new ProfileNotFoundException(userId));
    }
}
