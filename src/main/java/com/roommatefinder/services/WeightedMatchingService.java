package com.roommatefinder.services;

import com.roommatefinder.dtos.MatcherWeights;
import com.roommatefinder.entities.Profile;
import com.roommatefinder.entities.User;
import com.roommatefinder.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WeightedMatchingService implements ProfileMatchingService{

    private final ProfileRepository profileRepository;
    private final MatcherWeights weights;
    private final int[] scaleMaximums;

    public WeightedMatchingService(
            ProfileRepository profileRepository,
            @Value("${matcher.weights.bool}") double[] boolWeights,
            @Value("${matcher.weights.scale}") double[] scaleWeights,
            @Value("${matcher.scaleMaximums}") int[] scaleMaximums) {

        this.profileRepository = profileRepository;
        this.weights = new MatcherWeights(boolWeights, scaleWeights);
        this.scaleMaximums = scaleMaximums;

    }

    // this uses default weights defined in the application.yaml file
    public Map<Long, Integer> getUserMatches(Profile userProfile) {
        var pool = getMatchingPool(userProfile);
        Map<Long, Integer> matches = new HashMap<>();
        pool.forEach(candidate ->
                matches.put(candidate.getId(), getMatchPercent(userProfile, candidate, weights)));
        return matches;
    }

    // this uses custom weights passed in as a function argument.
    public Map<Long, Integer> getUserMatches(Profile userProfile, MatcherWeights customWeights) {
        var pool = getMatchingPool(userProfile);
        Map<Long, Integer> matches = new HashMap<>();
        pool.forEach(candidate ->
                matches.put(candidate.getId(), getMatchPercent(userProfile, candidate, customWeights)));
        return matches;
    }

    public int getUserMatch(Profile targetUser, Profile candidateUser) {
        Profile targetProf = profileRepository.findById(targetUser.getId()).orElseThrow();
        Profile candProf = profileRepository.findById(candidateUser.getId()).orElseThrow();
        return getMatchPercent(targetProf, candProf, weights);
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

    private Integer getMatchPercent(Profile target, Profile match, MatcherWeights weights) {
        double scoreSum = 0;
        double weightSum = 0;
        double[] boolWeights = weights.getBoolWeights();
        double[] scaleWeights = weights.getScaleWeights();
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
