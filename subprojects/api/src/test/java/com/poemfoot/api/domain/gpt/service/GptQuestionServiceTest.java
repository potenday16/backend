package com.poemfoot.api.domain.gpt.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.poemfoot.api.domain.GptQuestion;
import com.poemfoot.api.service.GptAnswerService;
import com.poemfoot.api.service.GptQuestionService;
import com.poemfoot.gpt.dto.request.GptChatPoemRequest;
import com.poemfoot.gpt.dto.response.chat.GptChatPoemResponse;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class GptQuestionServiceTest {

    @Autowired
    private GptQuestionService gptQuestionService;
    @Autowired
    private GptAnswerService gptAnswerService;

    @Test
    void saveQuestionTest() {
        List<String> words = List.of("강", "산", "바다");
        String location = "서울";

        GptChatPoemRequest request = new GptChatPoemRequest(words, location);
        GptChatPoemResponse response = GptChatPoemResponse.of("id", "object", "model", "message");

        GptQuestion question = gptQuestionService.saveQuestion(request,
                gptAnswerService.saveAnswer(response));

        assertThat(question.getQuestion()).isEqualTo(getQuestion(words, location));
        assertThat(question.getAnswer().getAnswer()).isEqualTo(response.getMessages().getFirst());
    }

    @Test
    void duplicateRequestTest() {
        List<String> words = List.of("강", "산", "바다");
        String location = "서울";

        GptChatPoemRequest request = new GptChatPoemRequest(words, location);
        GptChatPoemResponse response = GptChatPoemResponse.of("id", "object", "model", "message");
        gptQuestionService.saveQuestion(request, gptAnswerService.saveAnswer(response));

        GptChatPoemResponse duplicateResponse = gptQuestionService.requestPoem(request);

        assertThat(duplicateResponse.isReuse()).isTrue();
    }

    private String getQuestion(List<String> words, String location) {
        return Stream.concat(words.stream(), Stream.of(location))
                .collect(Collectors.joining(","));
    }
}
