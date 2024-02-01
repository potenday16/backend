package com.poemfoot.api.controller;

import static com.poemfoot.api.config.CommonConstants.DEVICE_ID;

import com.poemfoot.api.dto.response.card.CardListResponse;
import com.poemfoot.api.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Card", description = "카드 관련 API")
@RequestMapping("/cards")
public class CardController {

    private final CardService cardService;

    @GetMapping
    @Operation(summary = "특정 요청자의 카드 목록 조회")
    public ResponseEntity<CardListResponse> findCards(
            @RequestHeader(DEVICE_ID) String deviceId
    ) {
        return ResponseEntity.ok(cardService.findCards(deviceId));
    }

}
