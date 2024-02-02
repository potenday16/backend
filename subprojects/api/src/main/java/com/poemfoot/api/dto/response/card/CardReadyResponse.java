package com.poemfoot.api.dto.response.card;

public record CardReadyResponse(
        boolean isReady
) {
    public static CardReadyResponse from(boolean isReady) {
        return new CardReadyResponse(isReady);
    }
}
