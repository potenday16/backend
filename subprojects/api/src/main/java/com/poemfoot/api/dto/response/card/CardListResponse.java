package com.poemfoot.api.dto.response.card;

import java.util.List;

public record CardListResponse(
    List<CardResponse> cards
) {
    public static CardListResponse of(List<CardResponse> cards) {
        return new CardListResponse(cards);
    }
}
