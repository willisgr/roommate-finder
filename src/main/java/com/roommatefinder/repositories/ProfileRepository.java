package com.roommatefinder.repositories;

import com.roommatefinder.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    List<Profile> findProfilesByCity(String city);

    Object findByid(Long id);
}