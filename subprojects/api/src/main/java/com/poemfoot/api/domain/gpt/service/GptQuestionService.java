package com.poemfoot.api.domain.gpt.service;

import com.poemfoot.api.domain.gpt.domain.GptAnswer;
import com.poemfoot.api.domain.gpt.domain.GptQuestion;
import com.poemfoot.api.domain.gpt.repository.GptQuestionRepository;
import com.poemfoot.gpt.dto.request.GptChatPoemRequest;
import com.poemfoot.gpt.dto.response.chat.GptChatPoemResponse;
import com.poemfoot.gpt.service.GptPoemProvider;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GptQuestionService {

    private final GptPoemProvider gptPoemProvider;
    private final GptQuestionRepository gptQuestionRepository;

    @Transactional
    public GptChatPoemResponse requestPoem(GptChatPoemRequest request) {
        Optional<GptQuestion> question = gptQuestionRepository
                .findFirstByQuestion(getQuestion(request));

        if (question.isPresent()) {
            GptAnswer answer = question.get().getAnswer();
            return GptChatPoemResponse.of(
                    answer.getId().toString(),
                    answer.getObject(),
                    answer.getModel(),
                    answer.getAnswer());
        }
        return gptPoemProvider.completionChat(request);
    }

    @Transactional
    public GptQuestion saveQuestion(GptChatPoemRequest request, GptAnswer answer) {
        return gptQuestionRepository.save(new GptQuestion(getQuestion(request), answer));
    }

    private String getQuestion(GptChatPoemRequest request) {
        return Stream.concat(request.getWords().stream(), Stream.of(request.getLocation()))
                .collect(Collectors.joining(","));
    }
}
