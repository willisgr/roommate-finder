package com.roommatefinder.repositories;

import com.roommatefinder.entities.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
}