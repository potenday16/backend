package com.poemfoot.gpt.config;

import com.poemfoot.gpt.service.GptPoemProvider;
import com.theokanning.openai.service.OpenAiService;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Configuration
public class GptConfig {

    public static final int MAX_TOTAL_REQUEST_COUNT = 1;
    public static final AtomicInteger totalRequestCount = new AtomicInteger(0);

    @Value("${gpt.token}")
    private String token;

    @Bean
    public OpenAiService openAiService(){
        return new OpenAiService(token, Duration.ofSeconds(60));
    }

    @Bean
    public GptPoemProvider gptPoemProvider(OpenAiService openAiService){
        return new GptPoemProvider(openAiService);
    }

    @Scheduled(cron = "0 0 0 * * *") // 매일 자정에 실행
    public void resetCounter() {
        totalRequestCount.set(0);
    }
}
