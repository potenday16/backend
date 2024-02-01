package com.poemfoot.api.dto.response.member;

public record MemberResponse(
        Long id,
        String nickname
) {
    public static MemberResponse of(Long id, String nickname) {
        return new MemberResponse(id, nickname);
    }
}
