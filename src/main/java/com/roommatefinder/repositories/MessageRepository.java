package com.roommatefinder.repositories;

import com.roommatefinder.entities.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long> {
}