package com.spring.ia.config;

import com.spring.ia.tools.TimeTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TimeChatClientConfig {

    @Bean("timeChatClient")
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder,
                                 MessageChatMemoryAdvisor messageChatMemoryAdvisor,
                                 TimeTools timeTools) {
        return chatClientBuilder
                .defaultTools(timeTools)
                .defaultAdvisors(messageChatMemoryAdvisor)
                .build();
    }
}
