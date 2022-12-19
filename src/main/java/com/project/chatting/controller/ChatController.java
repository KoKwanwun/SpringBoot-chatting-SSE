package com.project.chatting.controller;

import com.project.chatting.domain.ChatMessage;
import com.project.chatting.domain.RSData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private List<ChatMessage> chatMessages = new ArrayList<>();

    public record writeMessageResponse(long id){
    }

    public record writeMessageRequest(String authorName, String content){
    }

    @PostMapping("/writeMessage")
    @ResponseBody
    public RSData<writeMessageResponse> writeMessage(@RequestBody writeMessageRequest request) {

        ChatMessage message = new ChatMessage(request.authorName(), request.content());

        chatMessages.add(message);

        return new RSData<>(
                "S-1",
                "메세지가 작성되었습니다",
                new writeMessageResponse(message.getId()));
    }

    @GetMapping("/messages")
    @ResponseBody
    public RSData<List<ChatMessage>> messages() {
        return new RSData<>(
                "S-1",
                "성공",
                chatMessages
        );
    }

}
