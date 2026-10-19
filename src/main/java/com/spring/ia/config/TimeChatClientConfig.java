package com.spring.ia.config;

import com.spring.ia.advisors.TokenUsageAuditAdvisor;
import com.spring.ia.tools.TimeTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TimeChatClientConfig {

    @Bean("timeChatClient")
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder,
                                 ChatMemory chatMemory, TimeTools timeTools) {
        Advisor loggerAdvisor = new SimpleLoggerAdvisor();
        Advisor tokenUsageAdvisor = new TokenUsageAuditAdvisor();
        Advisor memoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();
        return chatClientBuilder
                .defaultTools(timeTools)
                .defaultAdvisors(List.of(loggerAdvisor, memoryAdvisor,tokenUsageAdvisor))
                .build();
    }
}
