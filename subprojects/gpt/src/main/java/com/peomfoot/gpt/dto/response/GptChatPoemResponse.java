package com.peomfoot.gpt.dto.response;

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

    private List<PoemMessage> messages;

    private Usage usage;

    public static List<PoemMessage> toResponseListBy(List<ChatCompletionChoice> choices) {
        return choices.stream()
                .map(completionChoice -> PoemMessage.of(completionChoice.getMessage()))
                .toList();
    }

    public static GptChatPoemResponse of(ChatCompletionResult result) {
        return new GptChatPoemResponse(
                result.getId(),
                result.getObject(),
                result.getCreated(),
                result.getModel(),
                toResponseListBy(result.getChoices()),
                Usage.of(result.getUsage())
        );
    }
}
