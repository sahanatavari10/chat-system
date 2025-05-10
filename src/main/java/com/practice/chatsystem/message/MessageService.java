package com.practice.chatsystem.message;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public GetMessageResult getMessagesForUser(Long userId){
        Optional<List<Message>> maybeMessages = messageRepository.findBySenderIdOrReceiverId(userId, userId);
        if(maybeMessages.isEmpty()){
            return new GetMessageResult.NotFound();
        }
        return new GetMessageResult.IsPresent(maybeMessages.get());
    }

    public Message sendMessage(MessageDTO messageDTO){
        Message message = new Message(messageDTO.getSenderId(), messageDTO.getReceiverId(), messageDTO.getContent());
        return messageRepository.save(message);
    }
}
