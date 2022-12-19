package com.project.chatting.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ChatMessage {
    private long id;
    private LocalDateTime createDate;
    private String authorName;
    private String content;

    public ChatMessage(String authorName, String content) {
        this.id = ChatMessageIdGenerator.genNextId();
        this.createDate = LocalDateTime.now();
        this.authorName = authorName;
        this.content = content;
    }
}

class ChatMessageIdGenerator {
    private static long id = 0;

    public static long genNextId() {
        return ++id;
    }
}
