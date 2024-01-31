package com.poemfoot.api.domain.gpt.controller;

import com.poemfoot.api.domain.gpt.domain.GptAnswer;
import com.poemfoot.api.domain.gpt.service.GptAnswerService;
import com.poemfoot.api.domain.gpt.service.GptQuestionService;
import com.poemfoot.gpt.dto.request.GptChatPoemRequest;
import com.poemfoot.gpt.dto.response.chat.GptChatPoemResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "GPT", description = "GPT 시 생성 관련 API")
@RequestMapping("/gpt/v1")
public class GptPoemController {

    private final GptQuestionService gptQuestionService;
    private final GptAnswerService gptAnswerService;

    @Operation(summary = "Gpt 시 생성")
    @PostMapping("/completion/chat")
    public ResponseEntity<GptChatPoemResponse> completionChat(final @RequestBody GptChatPoemRequest request) {
        GptChatPoemResponse response = gptQuestionService.requestPoem(request);
        GptAnswer gptAnswer = gptAnswerService.saveAnswer(response);
        gptQuestionService.saveQuestion(request, gptAnswer);

        return ResponseEntity.ok(response);
    }
}
