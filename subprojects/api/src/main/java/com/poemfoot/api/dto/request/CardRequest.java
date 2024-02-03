package com.poemfoot.api.dto.request;

import com.poemfoot.api.domain.Words;

public record CardRequest(
        String location,
        Words words,
        String font,
        String fontColor,
        String background,
        double latitude,
        double longitude
) { }
