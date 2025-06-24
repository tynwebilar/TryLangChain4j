package com.example.langchain4jtest.config;

import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeepSeekApiConfig {

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.model.name:deepseek-chat}")
    private String modelName;
    @Value("${openai.model.baseurl:https://api.deepseek.com/v1}")

    private String baseUrl;

    @Value("${openai.timeout:60s}")
    private String timeout;

    @Bean
    public OpenAiChatModel openAiChatModel() {
        return OpenAiChatModel.builder()
                .baseUrl(baseUrl)
                .apiKey(apiKey)
                .modelName(modelName)
                .timeout(java.time.Duration.parse("PT" + timeout))
                .build();
    }
} 