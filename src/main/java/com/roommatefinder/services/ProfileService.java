package com.roommatefinder.services;

import com.roommatefinder.entities.Profile;
import com.roommatefinder.repositories.ProfileRepository;
import com.roommatefinder.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProfileService {
    public final ProfileRepository profileRepository;
    public final EntityManager entityManager;
    public final UserRepository userRepository;

    public void viewProfile(Long profileId) {
        //Viewing profile information for a given profile ID
        var profile = profileRepository.findById(profileId).orElseThrow();
        System.out.println("Profile ID: " + profile.getId());
        System.out.println("User Name: " + profile.getFirstName() + " " + profile.getLastName());
        System.out.println("User Email: " + profile.getUser().getEmail());
        System.out.println("Age: " + profile.getUserAge());
        System.out.println("Gender: " + profile.getGender());
        System.out.println("Match Gender?: " + (profile.getMatchGender() ? "Yes" : "No"));
        System.out.println("City: " + profile.getCity());
        System.out.println("Student?: " + (profile.getIsStudent() ? "Yes" : "No"));
        System.out.println("School: " + profile.getSchool());
        System.out.println("Major: " + profile.getCollegeMajor());
        System.out.println("Split Groceries?: " + (profile.getSharedGroceries() ? "Yes" : "No"));
        System.out.println("Pet Friendly? : " + (profile.getIsPetFriendly() ? "Yes" : "No"));
        System.out.println("Delegate Chores?: " + (profile.getDelegatedChores() ? "Yes" : "No"));
        System.out.println("Cleanliness: " + profile.getCleanlinessPreference());
        System.out.println("Social Preference: " + profile.getSocialPreference());
        System.out.println("Guest Preference: " + profile.getGuestPreference());
    }

    @Transactional
    public void addProfile() {
        // Implementation for adding a profile
        var user = userRepository.findById(1L).orElseThrow();
        var profile = Profile.builder()
                .user(user)
                .firstName("John")
                .lastName("Doe")
                .userAge((short) 25)
                .gender("Male")
                .matchGender(true)
                .city("New York")
                .isStudent(true)
                .school("NYU")
                .collegeMajor("Computer Science")
                .sharedGroceries(true)
                .isPetFriendly(false)
                .delegatedChores(true)
                .cleanlinessPreference((byte) 3)
                .socialPreference((byte) 4)
                .guestPreference((byte) 3)
                .build();
        profileRepository.save(profile);
    }

    @Transactional
    public void deleteProfile(Long profileId) {
        // Implementation for deleting a profile
        var profile = profileRepository.findById(profileId).orElseThrow();
        profileRepository.delete(profile);
    }

}