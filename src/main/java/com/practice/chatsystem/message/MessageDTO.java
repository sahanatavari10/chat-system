package com.practice.chatsystem.message;

import lombok.Data;

@Data
public class MessageDTO {
    private Long senderId;
    private Long receiverId;
    private String content;
}
