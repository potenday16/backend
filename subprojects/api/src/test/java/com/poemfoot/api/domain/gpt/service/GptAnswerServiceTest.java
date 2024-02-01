package com.poemfoot.api.domain.gpt.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.poemfoot.api.domain.GptAnswer;
import com.poemfoot.api.service.GptAnswerService;
import com.poemfoot.gpt.dto.response.chat.GptChatPoemResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class GptAnswerServiceTest {

    @Autowired
    private GptAnswerService gptAnswerService;

    @Test
    void saveAnswerTest() {
        GptChatPoemResponse response = GptChatPoemResponse.of("id", "object", "model", "message");

        GptAnswer answer = gptAnswerService.saveAnswer(response);

        assertThat(answer.getAnswer()).isEqualTo(response.getMessages().getFirst());
    }
}
