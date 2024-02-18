package com.poemfoot.gpt.service;

import static com.poemfoot.gpt.config.GptConfig.MAX_TOTAL_REQUEST_COUNT;
import static com.poemfoot.gpt.config.GptConfig.totalRequestCount;

import com.poemfoot.gpt.GptProvider;
import com.poemfoot.gpt.dto.request.GptChatPoemRequest;
import com.poemfoot.gpt.dto.response.chat.GptChatPoemResponse;
import com.poemfoot.gpt.exception.server.GptOverRequestException;
import com.theokanning.openai.service.OpenAiService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MockGptProviderImpl implements GptProvider {

    private final OpenAiService openAiService;

    @Override
    public Optional<GptChatPoemResponse> completionChat(GptChatPoemRequest request) {
        return Optional.of(new GptChatPoemResponse(
                """
                {
                  "제목": "test",
                  "내용": "test test test test"
                }
                """,
                false));
    }

    @Override
    public boolean validateGptRequest() {
        if (totalRequestCount.get() >= MAX_TOTAL_REQUEST_COUNT) {
            throw new GptOverRequestException();
        }
        return true;
    }
}
