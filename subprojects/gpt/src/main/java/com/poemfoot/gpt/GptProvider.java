package com.poemfoot.gpt;

import com.poemfoot.gpt.dto.request.GptChatPoemRequest;
import com.poemfoot.gpt.dto.response.chat.GptChatPoemResponse;
import java.util.Optional;

public interface GptProvider {
    Optional<GptChatPoemResponse> completionChat(GptChatPoemRequest request);
    boolean validateGptRequest();

}
