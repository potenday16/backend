package com.poemfoot.api.dto.response.card;

import com.poemfoot.api.domain.Card;
import com.poemfoot.api.domain.Words;
import java.sql.Timestamp;
import java.util.List;
import lombok.Builder;

@Builder
public record CardResponse(
        Long id,
        Long number,
        Timestamp createdTime,
        String nickname,
        String title,
        String content,
        Words words,
        String location,
        String font,
        String fontColor,
        String background,
        double latitude,
        double longitude
) {
    public static CardResponse of(Card card){
        return CardResponse.builder()
                .id(card.getId())
                .number(card.getNumber())
                .createdTime(card.getCreatedTime())
                .nickname(card.getMember().getNickname())
                .font(card.getFont())
                .fontColor(card.getFontColor())
                .background(card.getBackground())
                .title(card.getPoem().getTitle())
                .content(card.getPoem().getContent())
                .words(card.getPoem().getWords())
                .location(card.getPoem().getLocation())
                .latitude(card.getLatitude())
                .longitude(card.getLongitude())
                .build();

    }
}
