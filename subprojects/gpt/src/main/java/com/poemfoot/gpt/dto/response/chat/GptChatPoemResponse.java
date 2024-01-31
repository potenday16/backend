package com.poemfoot.gpt.dto.response.chat;

import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
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

    public GptChatPoemResponse(String id, String object, String model, List<String> messages, boolean reuse) {
        this.id = id;
        this.object = object;
        this.model = model;
        this.messages = messages;
        this.reuse = reuse;
    }

    public static List<String> toResponseListBy(List<ChatCompletionChoice> choices) {
        return choices.stream()
                .map(completionChoice -> completionChoice.getMessage().getContent())
                .toList();
    }

    public static GptChatPoemResponse of(ChatCompletionResult result) {
        return new GptChatPoemResponse(
                result.getId(),
                result.getObject(),
                result.getCreated(),
                result.getModel(),
                toResponseListBy(result.getChoices()),
                Usage.of(result.getUsage()),
                false
        );
    }

    public static GptChatPoemResponse of(String id, String object, String model, String message) {
        return new GptChatPoemResponse(
                id,
                object,
                model,
                List.of(message),
                true
        );
    }
}
