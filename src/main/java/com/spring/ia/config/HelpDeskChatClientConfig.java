package com.spring.ia.config;

import com.spring.ia.tools.TimeTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class HelpDeskChatClientConfig {

    @Value("classpath:/promptTemplates/helpDeskSystemPromptTemplate.st")
    Resource systemPromptTemplate;

    @Bean("helpDeskChatClient")
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder,
                                 MessageChatMemoryAdvisor messageChatMemoryAdvisor,
                                 TimeTools timeTools) {
        return chatClientBuilder
                .defaultSystem(systemPromptTemplate)
                .defaultTools(timeTools)
                .defaultAdvisors(messageChatMemoryAdvisor)
                .build();
    }

//    @Bean
//    ToolExecutionExceptionProcessor toolExecutionExceptionProcessor() {
//        return new DefaultToolExecutionExceptionProcessor(true);
//    }


}
