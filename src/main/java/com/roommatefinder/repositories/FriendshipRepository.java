package com.roommatefinder.repositories;

import com.roommatefinder.entities.Friendship;
import org.springframework.data.repository.CrudRepository;

public interface FriendshipRepository extends CrudRepository<Friendship, Long> {
}