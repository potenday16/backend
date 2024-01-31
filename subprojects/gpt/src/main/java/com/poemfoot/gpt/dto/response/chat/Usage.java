package com.poemfoot.gpt.dto.response.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Usage {

    private Long promptTokens;

    private Long completionTokens;

    private Long totalTokens;

    public static Usage of(com.theokanning.openai.Usage usage) {
        return new Usage(
                usage.getPromptTokens(),
                usage.getCompletionTokens(),
                usage.getTotalTokens()
        );
    }
}
