package com.poemfoot.api.controller;

import com.poemfoot.api.domain.GptAnswer;
import com.poemfoot.api.service.GptAnswerService;
import com.poemfoot.api.service.GptQuestionService;
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
@Tag(name = "Poem", description = "시 관련 API")
@RequestMapping("/api/v1/gpt")
public class PoemController {

    private final GptQuestionService gptQuestionService;
    private final GptAnswerService gptAnswerService;

    @Operation(summary = "Gpt 시 생성")
    @PostMapping("/completion/chat")
    public ResponseEntity<GptChatPoemResponse> completionChat(
            final @RequestBody GptChatPoemRequest request) {
        GptChatPoemResponse response = gptQuestionService.requestPoem(request);

        if (!response.isReuse()) {
            GptAnswer gptAnswer = gptAnswerService.saveAnswer(response);
            gptQuestionService.saveQuestion(request, gptAnswer);
        }
        return ResponseEntity.ok(response);
    }
}
