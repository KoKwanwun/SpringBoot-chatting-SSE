package com.project.chatting.controller;

import com.project.chatting.domain.ChatMessage;
import com.project.chatting.domain.RSData;
import com.project.chatting.sse.SseEmitters;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {
    private final SseEmitters sseEmitters;

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

        sseEmitters.noti("chat__messageAdded");

        return new RSData<>(
                "S-1",
                "메세지가 작성되었습니다",
                new writeMessageResponse(message.getId()));
    }

    public record MessagesRequest(Long fromId){
    }

    public record MessagesResponse(List<ChatMessage> messages, long count){
    }

    @GetMapping("/room")
    public String showRoom() {
        return "chat/chatRoom";
    }

    @GetMapping("/messages")
    @ResponseBody
    public RSData<MessagesResponse> messages(MessagesRequest request) {
        List<ChatMessage> messages = chatMessages;

        // 번호가 입력되었다면
        if(request.fromId != null){
            // 해당 번호의 채팅메세지가 전체 리스트에서의 배열인덱스 번호를 구함
            // 없다면 -1
            int idx = IntStream.range(0, messages.size())
                    .filter(i -> chatMessages.get(i).getId() == request.fromId)
                    .findFirst()
                    .orElse(-1);

            // 만약 존재한다면, 0번 ~ idx번까지 제거한 리스트로 만듦
            if(idx != -1){
                messages = messages.subList(idx + 1, messages.size());
            }
        }

        return new RSData<>(
                "S-1",
                "성공",
                new MessagesResponse(messages, messages.size())
        );
    }

}
