package com.poemfoot.gpt.controller;

import com.poemfoot.gpt.dto.request.GptChatPoemRequest;
import com.poemfoot.gpt.dto.response.GptChatPoemResponse;
import com.poemfoot.gpt.service.GptPoemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "GPT", description = "GPT 시 생성 관련 API")
@RequestMapping("/gpt/v1")
public class GptPoemController {

    private final GptPoemService gptPoemService;

    @Operation(summary = "Gpt 시 생성")
    @PostMapping("/completion/chat")
    public GptChatPoemResponse completionChat(final @RequestBody GptChatPoemRequest request) {

        return gptPoemService.completionChat(request);
    }
}
