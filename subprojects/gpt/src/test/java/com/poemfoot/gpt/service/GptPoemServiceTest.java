package com.poemfoot.gpt.service;

import static org.junit.jupiter.api.Assertions.*;

import com.poemfoot.gpt.domain.question.GptQuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class GptPoemServiceTest {

    @Autowired
    private GptPoemService gptPoemService;
    @Autowired
    private GptQuestionRepository gptQuestionRepository;

    @Test
    void saveQuestionTest() {

    }
}
