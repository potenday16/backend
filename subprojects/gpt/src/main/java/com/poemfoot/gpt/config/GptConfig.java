package com.poemfoot.gpt.config;

import com.poemfoot.gpt.GptProvider;
import com.poemfoot.gpt.service.GptProviderImpl;
import com.theokanning.openai.service.OpenAiService;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class GptConfig {

    public static final String MODEL = "gpt-4-turbo-preview";
    public static final String ROLE = "user";
    public static final Integer MAX_TOKENS = 500;
    public static final Integer MIN_TEXT_LIMIT = 70;
    public static final Integer MAX_TEXT_LIMIT = 80;
    public static final String MESSAGE = "너는 이제 시인이야."
            + " %s 3글자와 %s 위치에 대한 시를 제목과 내용을 구분해서 작성해줘."
            + " 내용은 공백 포함 %d자 이상 %d자 미만으로 작성해줘."
            + " 단 글자수에서 줄바꿈 문자는 제외해야 돼."
            + " 제목은 시에 관련된 한가지 단어로 작성해야 돼."
            + " 형식은 제목,내용을 가지는 json형식으로 반환해줘."
            + " 내용은 적당하게 줄바꿈을 해줘.";
    public static final String WORDS_DELIMITER = ", ";
    public static final AtomicInteger totalRequestCount = new AtomicInteger(0);
    public static Integer MAX_TOTAL_REQUEST_COUNT;

    @Value("${gpt.token}")
    private String token;

    @Bean
    public OpenAiService openAiService() {
        return new OpenAiService(token, Duration.ofSeconds(60));
    }

    @Bean
    public GptProvider gptPoemProvider(OpenAiService openAiService) {
        return new GptProviderImpl(openAiService);
    }

    @Scheduled(cron = "0 0 0 * * *") // 매일 자정에 실행
    public void resetCounter() {
        totalRequestCount.set(0);
    }

    @Value("${gpt.request.count}")
    private void setMaxTotalRequestCount(int maxRequestCount) {
        MAX_TOTAL_REQUEST_COUNT = maxRequestCount;
    }
}
