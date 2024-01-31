package com.poemfoot.gpt.service;

import com.poemfoot.gpt.config.GptConfig;
import com.poemfoot.gpt.dto.request.GptChatPoemRequest;
import com.poemfoot.gpt.dto.response.chat.GptChatPoemResponse;
import com.poemfoot.gpt.exception.badrequest.GptOverRequestException;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.service.OpenAiService;

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
    public GptChatPoemResponse completionChat(GptChatPoemRequest request) {

        validateGptRequest();

        ChatCompletionResult chatCompletion = openAiService.createChatCompletion(
                GptChatPoemRequest.of(request));
        gptRequestCountUp();

        return GptChatPoemResponse.of(chatCompletion);
    }

    private void validateGptRequest() {
        if (GptConfig.totalRequestCount >= GptConfig.MAX_TOTAL_REQUEST_COUNT) {
            throw new GptOverRequestException();
        }
    }

    private static void gptRequestCountUp() {
        GptConfig.totalRequestCount++;
        log.info("GptTotalRequestCount: {}", GptConfig.totalRequestCount);
    }
}
