package com.poemfoot.gpt.service;

import static com.poemfoot.gpt.config.GptConfig.MAX_TOTAL_REQUEST_COUNT;
import static com.poemfoot.gpt.config.GptConfig.totalRequestCount;

import com.poemfoot.gpt.config.GptConfig;
import com.poemfoot.gpt.dto.request.GptChatPoemRequest;
import com.poemfoot.gpt.dto.response.chat.GptChatPoemResponse;
import com.poemfoot.gpt.exception.badrequest.GptOverRequestException;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.service.OpenAiService;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GptPoemProvider {

    private final OpenAiService openAiService;

    @Transactional
    public Optional<GptChatPoemResponse> completionChat(GptChatPoemRequest request) {

        validateGptRequest();

        ChatCompletionResult chatCompletion = openAiService.createChatCompletion(
                GptChatPoemRequest.of(request));
        if(gptRequestCountUp()){
            return Optional.of(GptChatPoemResponse.of(chatCompletion));
        }
        return Optional.empty();
    }

    private void validateGptRequest() {
        if (totalRequestCount.get() >= MAX_TOTAL_REQUEST_COUNT) {
            throw new GptOverRequestException();
        }
    }

    private boolean gptRequestCountUp() {
        int currentCount = totalRequestCount.get();
        return totalRequestCount.compareAndSet(currentCount, currentCount + 1);
    }
}
