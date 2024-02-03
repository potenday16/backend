package com.poemfoot.api.dto.request;

public record CardRequest(
        Long poemId,
        Long w3wResultId,
        String font,
        String fontColor,
        String background
) { }
