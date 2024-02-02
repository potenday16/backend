package com.poemfoot.w3w.config;

import com.what3words.javawrapper.What3WordsV3;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class W3wConfig {

    public static final int MAX_TOTAL_REQUEST_COUNT = 1;
    public static final AtomicInteger totalRequestCount = new AtomicInteger(0);

    @Value("${w3w.api.key}")
    private String apiKey;

    @Bean
    public What3WordsV3 what3WordsV3() {
        return new What3WordsV3(apiKey);
    }

    @Scheduled(cron = "0 0 0 * * *") // 매일 자정에 실행
    public void resetCounter() {
        totalRequestCount.set(0);
    }
}
