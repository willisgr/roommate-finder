package com.roommatefinder.mappers;

import com.roommatefinder.dtos.MessageResponse;
import com.roommatefinder.dtos.SendMessageRequest;
import com.roommatefinder.entities.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    @Mapping(source = "sender.id", target = "senderId")
    @Mapping(source = "recipient.id", target = "recipientId")
    MessageResponse toDto(Message message);

    List<MessageResponse> toDtoList(List<Message> messageList);

    @Mapping(target = "id", ignore = true)
    Message toEntity(SendMessageRequest request);
}
