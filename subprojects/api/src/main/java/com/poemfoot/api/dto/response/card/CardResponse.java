package com.poemfoot.api.dto.response.card;

import com.poemfoot.api.domain.Card;
import java.sql.Timestamp;
import java.util.List;

public record CardResponse(
        Long id,
        Long backGroundId,
        Timestamp createdTime,
        String nickname,
        String title,
        String content,
        String words
) {
    public static CardResponse of(Card card){

        return new CardResponse(
                card.getId(),
                card.getBackGroundImage().getId(),
                card.getCreatedTime(),
                card.getMember().getNickname(),
                card.getPoem().getTitle(),
                card.getPoem().getContent(),
                card.getPoem().getWords()
        );
    }
}
