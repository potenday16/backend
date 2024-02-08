package com.poemfoot.api.dto.request;

public record MemberRequest(
        String nickname,
        String deviceOsType
) { }
