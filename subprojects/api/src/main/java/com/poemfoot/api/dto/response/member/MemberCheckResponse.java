package com.poemfoot.api.dto.response.member;

public record MemberCheckResponse(
        Boolean isRegistered,
        String nickname
) {
    public static MemberCheckResponse of(Boolean isRegistered, String nickname) {
        return new MemberCheckResponse(isRegistered, nickname);
    }
}
