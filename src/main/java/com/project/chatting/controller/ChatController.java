package com.project.chatting.controller;

import com.project.chatting.domain.ChatMessage;
import com.project.chatting.domain.RSData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private List<ChatMessage> chatMessages = new ArrayList<>();

    public record writeMessageResponse(long id){
    }

    @PostMapping("/writeMessage")
    @ResponseBody
    public RSData<writeMessageResponse> writeMessage() {

        ChatMessage message = new ChatMessage("홍길동", "안녕하세요.");

        return new RSData(
                "S-1",
                "메세지가 작성되었습니다",
                new writeMessageResponse(message.getId()));
    }

}
