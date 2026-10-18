package com.roommatefinder.repositories;

import com.roommatefinder.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}