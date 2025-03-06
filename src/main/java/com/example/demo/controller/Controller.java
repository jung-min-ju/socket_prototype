package com.example.demo.controller;

import com.example.demo.dto.ChatMessageReq;
import com.example.demo.dto.ChatMessageRes;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class Controller {

    private static final Logger log = LoggerFactory.getLogger(Controller.class);

    @MessageMapping("/chat.{chatRoomId}")
    @SendTo("/sub/chat.{chatRoomId}")
    public ChatMessageRes sendMessage(ChatMessageReq request, @DestinationVariable Long chatRoomId) {
        return new ChatMessageRes(request.userId(), request.content());
    }

    @MessageExceptionHandler
    public void handleException(RuntimeException e){
        log.info("Exception : {}", e.getMessage());
    }
}
