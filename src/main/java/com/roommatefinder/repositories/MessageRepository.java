package com.roommatefinder.repositories;

import com.roommatefinder.entities.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long> {
    Iterable<Message> findBySender_IdOrRecipient_IdOrderByTimeSentAsc(Long senderId, Long recipientId);
}