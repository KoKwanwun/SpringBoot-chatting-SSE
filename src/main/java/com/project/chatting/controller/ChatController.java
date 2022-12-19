package com.project.chatting.controller;

import com.project.chatting.domain.ChatMessage;
import com.project.chatting.domain.RSData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/chat")
public class ChatController {

    @PostMapping("/writeMessage")
    @ResponseBody
    public RSData<ChatMessage> writeMessage() {

        ChatMessage message = new ChatMessage("홍길동", "안녕하세요.");

        return new RSData("S-1", "메세지가 작성되었습니다", message);
    }

}
