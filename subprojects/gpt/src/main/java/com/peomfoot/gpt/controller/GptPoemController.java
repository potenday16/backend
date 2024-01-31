package com.peomfoot.gpt.controller;

import com.peomfoot.gpt.dto.request.GptChatPoemRequest;
import com.peomfoot.gpt.dto.response.GptChatPoemResponse;
import com.peomfoot.gpt.service.GptPoemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gpt/v1")
public class GptPoemController {

    private final GptPoemService gptPoemService;

    @PostMapping("/completion/chat")
    public GptChatPoemResponse completionChat(final @RequestBody GptChatPoemRequest request) {

        return gptPoemService.completionChat(request);
    }
}
