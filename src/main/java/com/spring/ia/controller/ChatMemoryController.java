package com.spring.ia.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ChatMemoryController {

    private final ChatClient chatClient;

    public ChatMemoryController(@Qualifier("chatMemoryClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/chat-memory")
    public ResponseEntity<String> chat(@RequestParam("message") String message) {
        return ResponseEntity.ok(chatClient
                .prompt()
                .user(message)
                .call().content());
    }

}
