package com.poemfoot.w3w.config;

import com.what3words.javawrapper.What3WordsV3;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class W3wConfig {
    @Value("${w3w.api.key}")
    private String apiKey;

    @Bean
    public What3WordsV3 what3WordsV3() {
        return new What3WordsV3(apiKey);
    }
}
