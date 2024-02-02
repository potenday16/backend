package com.poemfoot.api.domain.poem;

import lombok.Builder;

@Builder
public record PoemAnswer(
        String title,
        String content
) {
    public static PoemAnswer of(String title, String content) {
        return PoemAnswer.builder()
                .title(title)
                .content(content)
                .build();
    }
}
