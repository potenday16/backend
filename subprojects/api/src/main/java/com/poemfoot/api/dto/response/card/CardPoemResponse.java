package com.poemfoot.api.dto.response.card;

import com.poemfoot.api.domain.W3wResult;
import com.poemfoot.api.domain.Words;
import com.poemfoot.api.domain.poem.Poem;
import java.sql.Timestamp;
import lombok.Builder;

@Builder
public record CardPoemResponse(
        Long poemId,
        Long w3wResultId,
        Timestamp createdTime,
        String address,
        String title,
        String content,
        Words words,
        String location) {
    public static CardPoemResponse of(Poem poem, W3wResult w3wResult, String address) {
        return CardPoemResponse.builder()
                .poemId(poem.getId())
                .w3wResultId(w3wResult.getId())
                .createdTime(poem.getCreatedTime())
                .address(address)
                .title(poem.getTitle())
                .content(poem.getContent())
                .words(poem.getWords())
                .location(poem.getLocation())
                .build();
    }
}
