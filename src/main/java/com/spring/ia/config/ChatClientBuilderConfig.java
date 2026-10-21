package com.spring.ia.config;

import com.spring.ia.advisors.TokenUsageAuditAdvisor;
import org.springframework.ai.chat.client.ChatClientBuilderCustomizer;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBooleanProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Defaults shared by every {@code ChatClient} of the application.
 * <p>
 * Spring AI applies each {@link ChatClientBuilderCustomizer} to every {@code ChatClient.Builder}
 * it creates (the builder is a prototype bean), so the configs only declare what is specific
 * to their client: memory, RAG, semantic cache, tools, system prompt...
 * <p>
 * Each customizer can be switched off with its property; when the property is not set,
 * the customizer is enabled.
 */
@Configuration
public class ChatClientBuilderConfig {

    /** Logs every request and response (DEBUG level on the advisor package). */
    @Bean
    @ConditionalOnBooleanProperty(name = "app.chat-client.advisors.logger.enabled", matchIfMissing = true)
    ChatClientBuilderCustomizer loggerAdvisorCustomizer() {
        return chatClientBuilder -> chatClientBuilder.defaultAdvisors(new SimpleLoggerAdvisor());
    }

    /** Logs the prompt / completion token usage of every call. */
    @Bean
    @ConditionalOnBooleanProperty(name = "app.chat-client.advisors.token-usage.enabled", matchIfMissing = true)
    ChatClientBuilderCustomizer tokenUsageAdvisorCustomizer() {
        return chatClientBuilder -> chatClientBuilder.defaultAdvisors(new TokenUsageAuditAdvisor());
    }
}
