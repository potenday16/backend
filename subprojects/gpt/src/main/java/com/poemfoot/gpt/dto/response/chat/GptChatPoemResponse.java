package com.poemfoot.gpt.dto.response.chat;

import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GptChatPoemResponse {

    private String id;

    private String object;

    private Long created;

    private String model;

    private List<String> messages;

    private Usage usage;

    private boolean reuse;

    public static List<String> toResponseListBy(List<ChatCompletionChoice> choices) {
        return choices.stream()
                .map(completionChoice -> completionChoice.getMessage().getContent())
                .toList();
    }

    public static GptChatPoemResponse of(ChatCompletionResult result) {
        return GptChatPoemResponse.builder()
                .id(result.getId())
                .object(result.getObject())
                .model(result.getModel())
                .created(result.getCreated())
                .messages(toResponseListBy(result.getChoices()))
                .usage(Usage.of(result.getUsage()))
                .reuse(false)
                .build();
    }

    public GptChatPoemResponse(String message) {
        this.messages = List.of(message);
        this.reuse = true;
    }
}
