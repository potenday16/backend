package com.poemfoot.gpt.dto.request;

import static com.poemfoot.gpt.config.GptConfig.*;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GptChatPoemRequest {

    private List<String> words;
    private String location;

    public static ChatCompletionRequest of(GptChatPoemRequest request) {
        return ChatCompletionRequest.builder()
                .model(MODEL)
                .messages(convertChatMessage(request))
                .maxTokens(MAX_TOKENS)
                .build();
    }

    private static List<ChatMessage> convertChatMessage(GptChatPoemRequest request) {
        String message = MESSAGE
                .formatted(
                        String.join(WORDS_DELIMITER, request.getWords()), request.location,
                        MIN_TEXT_LIMIT, MAX_TEXT_LIMIT
                );

        return List.of(new ChatMessage(ROLE, message));
    }
}
