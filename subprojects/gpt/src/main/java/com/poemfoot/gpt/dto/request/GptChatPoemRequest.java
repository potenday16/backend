package com.poemfoot.gpt.dto.request;

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

    private static final String MODEL = "gpt-4-turbo-preview";
    private static final String ROLE = "user";
    private static final Integer MAX_TOKENS = 100;

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
        String message = "%s 3글자와 %s 위치에 대한 시를 써줘.".formatted(
                String.join(", ", request.getWords()),
                request.location);

        return List.of(new ChatMessage(ROLE, message));
    }
}
