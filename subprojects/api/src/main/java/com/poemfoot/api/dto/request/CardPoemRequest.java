package com.poemfoot.api.dto.request;

public record CardPoemRequest(
        String location,
        String address,
        double latitude,
        double longitude
) {}
