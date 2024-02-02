package com.poemfoot.api.dto.response.card;

import com.poemfoot.api.domain.Card;
import com.poemfoot.api.domain.Words;
import java.sql.Timestamp;
import java.util.List;
import lombok.Builder;

@Builder
public record CardResponse(
        Long id,
        Timestamp createdTime,
        String nickname,
        String title,
        String content,
        Words words,
        String font,
        String fontColor,
        String background
) {
    public static CardResponse of(Card card){
        return CardResponse.builder()
                .id(card.getId())
                .createdTime(card.getCreatedTime())
                .nickname(card.getMember().getNickname())
                .font(card.getFont())
                .fontColor(card.getFontColor())
                .background(card.getBackground())
                .title(card.getPoem().getTitle())
                .content(card.getPoem().getContent())
                .words(card.getPoem().getWords())
                .build();

    }
}
