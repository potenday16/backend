package com.peomfoot.gpt.service;

import com.peomfoot.gpt.dto.request.GptChatPoemRequest;
import com.peomfoot.gpt.dto.response.GptChatPoemResponse;
import com.peomfoot.gpt.dto.response.PoemMessage;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.service.OpenAiService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GptPoemService {
    private final OpenAiService openAiService;


    @Transactional
    public GptChatPoemResponse completionChat(GptChatPoemRequest request) {
        ChatCompletionResult chatCompletion = openAiService.createChatCompletion(GptChatPoemRequest.of(request));
        GptChatPoemResponse response = GptChatPoemResponse.of(chatCompletion);

        List<String> messages = response.getMessages().stream()
                .map(PoemMessage::getMessage)
                .toList();

        return response;
    }
}
