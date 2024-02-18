package com.poemfoot.gpt.service;

import static com.poemfoot.gpt.config.GptConfig.MAX_TOTAL_REQUEST_COUNT;
import static com.poemfoot.gpt.config.GptConfig.totalRequestCount;

import com.poemfoot.gpt.GptProvider;
import com.poemfoot.gpt.dto.request.GptChatPoemRequest;
import com.poemfoot.gpt.dto.response.chat.GptChatPoemResponse;
import com.poemfoot.gpt.exception.server.GptOverRequestException;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.service.OpenAiService;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GptProviderImpl implements GptProvider {

    private final OpenAiService openAiService;

    @Override
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

    @Override
    public boolean validateGptRequest() {
        if (totalRequestCount.get() >= MAX_TOTAL_REQUEST_COUNT) {
            throw new GptOverRequestException();
        }
        return true;
    }

    private boolean gptRequestCountUp() {
        int currentCount = totalRequestCount.get();
        return totalRequestCount.compareAndSet(currentCount, currentCount + 1);
    }
}
