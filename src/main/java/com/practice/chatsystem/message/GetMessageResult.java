package com.practice.chatsystem.message;

import java.util.List;

public sealed interface GetMessageResult {
    record IsPresent(List<Message> messageList) implements GetMessageResult{
    }

    record NotFound() implements GetMessageResult{
    }
}
