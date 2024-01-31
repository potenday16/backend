package com.poemfoot.gpt.service;

import com.poemfoot.gpt.config.GptConfig;
import com.poemfoot.gpt.domain.answer.GptAnswer;
import com.poemfoot.gpt.domain.answer.GptAnswerRepository;
import com.poemfoot.gpt.domain.question.GptQuestion;
import com.poemfoot.gpt.domain.question.GptQuestionRepository;
import com.poemfoot.gpt.dto.request.GptChatPoemRequest;
import com.poemfoot.gpt.dto.response.chat.GptChatPoemResponse;
import com.poemfoot.gpt.exception.badrequest.GptOverRequestException;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.service.OpenAiService;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GptPoemService {

    private final OpenAiService openAiService;
    private final GptAnswerRepository answerRepository;
    private final GptQuestionRepository questionRepository;

    @Transactional
    public GptChatPoemResponse completionChat(GptChatPoemRequest request) {
        Optional<GptQuestion> question = questionRepository.findFirstByQuestion(
                getQuestion(request.getWords(),
                        request.getLocation()));
        if (question.isPresent()) {
            GptAnswer answer = question.get().getAnswer();
            return GptChatPoemResponse.of(
                    answer.getId().toString(),
                    answer.getObject(),
                    answer.getModel(),
                    answer.getAnswer());
        }
        validateGptRequest();

        ChatCompletionResult chatCompletion = openAiService.createChatCompletion(
                GptChatPoemRequest.of(request));
        gptRequestCountUp();

        GptChatPoemResponse response = GptChatPoemResponse.of(chatCompletion);
        GptAnswer answer = saveAnswer(getAnswer(response), response.getObject(),
                response.getModel());
        saveQuestion(getQuestion(request.getWords(), request.getLocation()), answer);

        return response;
    }

    public GptAnswer saveAnswer(String answer, String object, String model) {
        return answerRepository.save(new GptAnswer(answer, object, model));
    }

    public GptQuestion saveQuestion(String question, GptAnswer answer) {
        return questionRepository.save(new GptQuestion(question, answer));
    }

    private void validateGptRequest() {
        if (GptConfig.totalRequestCount >= GptConfig.MAX_TOTAL_REQUEST_COUNT) {
            throw new GptOverRequestException();
        }
    }

    private String getQuestion(List<String> words, String location) {
        return Stream.concat(words.stream(), Stream.of(location))
                .collect(Collectors.joining(","));
    }

    private String getAnswer(GptChatPoemResponse response) {
        return responseToMessage(response).stream()
                .filter(Objects::nonNull)
                .collect(Collectors.joining());
    }

    private List<String> responseToMessage(GptChatPoemResponse response) {
        return response.getMessages().stream()
                .toList();
    }

    private static void gptRequestCountUp() {
        GptConfig.totalRequestCount++;
        log.info("GptTotalRequestCount: {}", GptConfig.totalRequestCount);
    }
}
