package com.poemfoot.api.dto.response.card;

import com.poemfoot.api.domain.Words;
import com.poemfoot.api.domain.poem.Poem;
import lombok.Builder;

@Builder
public record CardPoemResponse(
        String title,
        String content,
        Words words,
        String location
) {
    public static CardPoemResponse of(Poem poem) {
        return CardPoemResponse.builder()
                .title(poem.getTitle())
                .content(poem.getContent())
                .words(poem.getWords())
                .location(poem.getLocation())
                .build();
    }
}
