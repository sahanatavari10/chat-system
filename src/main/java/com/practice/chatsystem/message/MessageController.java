package com.practice.chatsystem.message;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chat/message")
@AllArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @PostMapping("/send")
    public ResponseEntity<Message> send(@RequestBody MessageDTO messageDTO){
        Message message = messageService.sendMessage(messageDTO);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Message>> getMessagesForUser(@PathVariable Long userId){
        GetMessageResult result = messageService.getMessagesForUser(userId);
        return switch (result){
            case GetMessageResult.IsPresent isPresent -> ResponseEntity.ok(isPresent.messageList());
            case GetMessageResult.NotFound ignored -> ResponseEntity.notFound().build();
        };
    }
}
