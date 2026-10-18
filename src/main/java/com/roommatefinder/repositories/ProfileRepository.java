package com.roommatefinder.repositories;

import com.roommatefinder.entities.Profile;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
    List<Profile> findProfilesByCity(String city);
}