package com.poemfoot.api.dto.request;

public record CardPoemRequest(
        String location,
        double latitude,
        double longitude
) {}
