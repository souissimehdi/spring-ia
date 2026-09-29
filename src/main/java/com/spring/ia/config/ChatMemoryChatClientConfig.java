package com.spring.ia.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatMemoryChatClientConfig {

    @Bean
    @Qualifier("chatMemoryClient")
    public ChatClient chatMemoryClient(ChatClient.Builder chatClientBuilder, ChatMemory chatMemory) {
        MessageChatMemoryAdvisor messageChatMemoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();
        SimpleLoggerAdvisor simpleLoggerAdvisor = new SimpleLoggerAdvisor();
        return  chatClientBuilder
                .defaultAdvisors(messageChatMemoryAdvisor, simpleLoggerAdvisor)
                .build();
    }
}
