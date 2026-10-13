package com.spring.ia.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SemanticChatController {

    private final ChatClient chatClient;

    public SemanticChatController(@Qualifier("semanticChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/semantic-chat")
    public String chat(@RequestParam("message") String message) {
        return chatClient
                .prompt()
                .user(message)
                .call().content();
    }


}
