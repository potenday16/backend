package com.poemfoot.api.service;

import com.poemfoot.api.domain.GptAnswer;
import com.poemfoot.api.repository.GptAnswerRepository;
import com.poemfoot.gpt.dto.response.chat.GptChatPoemResponse;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GptAnswerService {

    private final GptAnswerRepository gptAnswerRepository;

    @Transactional
    public GptAnswer saveAnswer(GptChatPoemResponse response) {

        return gptAnswerRepository.save(new GptAnswer(getAnswer(response), response.getObject(),
                response.getObject()));
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
}
