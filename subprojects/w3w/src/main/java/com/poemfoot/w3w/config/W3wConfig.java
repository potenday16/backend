package com.poemfoot.w3w.config;

import com.poemfoot.w3w.W3wProvider;
import com.poemfoot.w3w.impl.MockW3wProviderImpl;
import com.poemfoot.w3w.impl.W3wProviderImpl;
import com.what3words.javawrapper.What3WordsV3;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class W3wConfig {

    public static int MAX_TOTAL_REQUEST_COUNT;
    public static final AtomicInteger totalRequestCount = new AtomicInteger(0);

    @Value("${w3w.api.key}")
    private String apiKey;

    @Bean
    public What3WordsV3 what3WordsV3() {
        return new What3WordsV3(apiKey);
    }

    @Bean
    public W3wProvider w3wProvider(What3WordsV3 what3WordsV3) {
        return new MockW3wProviderImpl(what3WordsV3);
    }

    @Scheduled(cron = "0 0 0 * * *") // 매일 자정에 실행
    public void resetCounter() {
        totalRequestCount.set(0);
    }

    @Value("${w3w.request.count}")
    private void setMaxTotalRequestCount(int maxRequestCount) {
        MAX_TOTAL_REQUEST_COUNT = maxRequestCount;
    }
}
