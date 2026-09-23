package com.spring.ia.config;

import org.springframework.ai.bedrock.converse.BedrockChatOptions;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
        var chatOptions = BedrockChatOptions.builder()
                .model("eu.anthropic.claude-sonnet-5")
//                .temperature(0.8)
                .maxTokens(200);
        return chatClientBuilder
                .defaultOptions(chatOptions)
//                .defaultAdvisors(new SimpleLoggerAdvisor())
                .defaultSystem("""
                        You are an internal HR assistant. Your role is to help\s
                        employees with questions related to HR policies, such as\s
                        leave policies, working hours, benefits, and code of conduct.
                        If a user asks for help with anything outside of these topics,\s
                        kindly inform them that you can only assist with queries related to\s
                        HR policies.
                        """)
                .defaultUser("How can you help me ?")
                .build();
    }
}
