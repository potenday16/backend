package com.poemfoot.api.dto.request;

import java.util.List;

public record CardRequest(
        String location,
        String font,
        String fontColor,
        String background,
        double latitude,
        double longitude
) { }
