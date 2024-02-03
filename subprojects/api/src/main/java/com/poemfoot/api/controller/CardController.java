package com.poemfoot.api.controller;

import static com.poemfoot.api.config.CommonConstants.DEVICE_ID;

import com.poemfoot.api.dto.request.CardPoemRequest;
import com.poemfoot.api.dto.request.CardRequest;
import com.poemfoot.api.dto.response.card.CardListResponse;
import com.poemfoot.api.dto.response.card.CardPoemResponse;
import com.poemfoot.api.dto.response.card.CardResponse;
import com.poemfoot.api.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Card", description = "카드 관련 API")
@RequestMapping("/api/v1/cards")
public class CardController {

    private final CardService cardService;

    @GetMapping
    @Operation(summary = "특정 요청자의 카드 목록 조회")
    public ResponseEntity<CardListResponse> findCards(
            @RequestHeader(DEVICE_ID) String deviceId
    ) {
        return ResponseEntity.ok(cardService.findCards(deviceId));
    }

    @PostMapping
    @Operation(summary = "카드 생성 (생성 전, 시 생성 필요함)")
    public ResponseEntity<CardResponse> saveCard(
            @RequestHeader(DEVICE_ID) String deviceId,
            @RequestBody CardRequest cardRequest
    ) {
        return ResponseEntity.ok(cardService.saveCard(deviceId, cardRequest));
    }

    @GetMapping("/{id}")
    @Operation(summary = "특정 카드 조회")
    public ResponseEntity<CardResponse> findCard(
            @PathVariable("id") Long cardId
    ) {
        return ResponseEntity.ok(cardService.findCard(cardId));
    }

    @PostMapping("/poem")
    @Operation(summary = "시 생성")
    public ResponseEntity<CardPoemResponse> requestPoem(
            @RequestBody CardPoemRequest request
    ){
        return ResponseEntity.ok(cardService.getPoem(request.location(),
                request.latitude(), request.longitude()));
    }

    @GetMapping("/readiness")
    @Operation(summary = "카드 작성 유효성 검사")
    public ResponseEntity<Object> checkReadySaveCard(){
        return ResponseEntity.ok(cardService.checkReadiness());
    }
}
