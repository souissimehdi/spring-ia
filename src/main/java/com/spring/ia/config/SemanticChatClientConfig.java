package com.spring.ia.config;

import org.springframework.ai.chat.cache.semantic.SemanticCacheAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SemanticChatClientConfig {

    @Bean("semanticChatClient")
    public ChatClient semanticChatClient(ChatClient.Builder chatClientBuilder,
                                         SemanticCacheAdvisor semanticCacheAdvisor) {
        return chatClientBuilder
                .defaultAdvisors(semanticCacheAdvisor)
                .build();
    }
}
