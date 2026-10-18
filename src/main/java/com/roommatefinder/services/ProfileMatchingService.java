package com.roommatefinder.services;

import com.roommatefinder.entities.Profile;
import com.roommatefinder.entities.User;
import com.roommatefinder.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProfileMatchingService {

    private  final ProfileRepository profileRepository;
    private final double[] boolWeights = new double[] {1, 1, 1};
    private final double[] scaleWeights = new double[] {1, 1, 1};
    private final double[] scaleMaximums = new double[] {5, 5 ,3};

    public ProfileMatchingService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Map<Long, Integer> getUserMatches(User user) {
        Profile userProfile = profileRepository.findById(user.getId()).orElseThrow();
        var pool = getMatchingPool(userProfile);
        Map<Long, Integer> matches = new HashMap<>();
        pool.forEach(p -> matches.put(p.getId(), getMatchPercent(userProfile, p)));
        return matches;
    }

    private List<Profile> getMatchingPool(Profile userProfile) {

        // narrow down pool to within user's city
        var pool = profileRepository.findProfilesByCity(userProfile.getCity());

        // remove profiles that don't match gender preference
        if (userProfile.getMatchGender() == true) {
            pool.removeIf(p ->
                    !p.getGender().equals(userProfile.getGender()));
        } else {
            pool.removeIf(p ->
                    p.getMatchGender() == true && !userProfile.getGender().equals(p.getGender()));
        }
        // remove target user from pool
        pool.removeIf(p -> p.getId().equals(userProfile.getId()));

        return pool;
    }

    private Integer getMatchPercent(Profile target, Profile match) {
        double scoreSum = 0;
        double weightSum = 0;
        var tb = target.getBoolPreferences();
        var mb = match.getBoolPreferences();
        for (int i = 0; i < tb.length; i++) {
            if (tb[i] == mb[i]) {
                scoreSum += 1 * boolWeights[i];
            }
            weightSum += boolWeights[i];
        }
        var ts = target.getScalePreferences();
        var ms = match.getScalePreferences();
        for (int i = 0; i < ts.length; i++) {
            var diff = Math.abs(ts[i] - ms[i]);
            var part = Math.max(0 , 1 - (diff / scaleMaximums[i]));
            scoreSum += part * scaleWeights[i];
            weightSum += scaleWeights[i];
        }
        return (int) ((scoreSum / weightSum) * 100);
    }
}
