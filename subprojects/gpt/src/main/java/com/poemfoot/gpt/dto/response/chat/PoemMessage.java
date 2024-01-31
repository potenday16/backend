package com.poemfoot.gpt.dto.response.chat;

import com.theokanning.openai.completion.chat.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PoemMessage {

    private String role;

    private String message;

    public static PoemMessage of(ChatMessage chatMessage) {
        return new PoemMessage(
                chatMessage.getRole(),
                chatMessage.getContent()
        );
    }
}
