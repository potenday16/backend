package com.peomfoot.gpt.config;

import com.theokanning.openai.service.OpenAiService;
import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class GptConfig {

    @Value("${gpt.token}")
    private String token;

    @Bean
    public OpenAiService openAiService(){
        return new OpenAiService(token, Duration.ofSeconds(60));
    }

}
