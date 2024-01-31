package com.poemfoot.gpt.service;

import com.poemfoot.gpt.domain.question.GptQuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class GptPoemProviderTest {

    @Autowired
    private GptPoemProvider gptPoemProvider;
    @Autowired
    private GptQuestionRepository gptQuestionRepository;

    @Test
    void saveQuestionTest() {

    }
}
