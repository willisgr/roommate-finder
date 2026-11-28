package com.roommatefinder.mappers;

import com.roommatefinder.dtos.MessageResponse;
import com.roommatefinder.dtos.SendMessageRequest;
import com.roommatefinder.entities.Message;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.Instant;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class MessageMapper {

    @Mapping(source = "sender.id", target = "senderId")
    @Mapping(source = "recipient.id", target = "recipientId")
    public abstract MessageResponse toDto(Message message);

    public abstract List<MessageResponse> toDtoList(List<Message> messageList);

    @Mapping(target = "id", ignore = true)
    public abstract Message toEntity(SendMessageRequest request);

    @AfterMapping
    protected void setTimeSent(@MappingTarget Message message) {
        if (message.getTimeSent() == null) {
            message.setTimeSent(Instant.now());
        }
    }
}
