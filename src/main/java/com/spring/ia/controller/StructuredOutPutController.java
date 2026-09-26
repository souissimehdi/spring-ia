package com.spring.ia.controller;

import com.spring.ia.model.CountryCities;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StructuredOutPutController {

    private final ChatClient chatClient;

    public StructuredOutPutController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }

    @GetMapping("/chat-bean")
    public ResponseEntity chatBean(@RequestParam("message") String message) {
        CountryCities countryCities = chatClient
                .prompt()
                .user(message)
                .call().entity(CountryCities.class);
        return ResponseEntity.ok(countryCities);
    }

}
