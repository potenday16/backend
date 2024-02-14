package com.poemfoot.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CardPoemRequest(
        @NotBlank(message = "위치 단어는 공백일 수 없습니다.")
        String location,
        @NotBlank(message = "현재 위치는 공백일 수 없습니다.")
        String address,
        double latitude,
        double longitude
) {}
