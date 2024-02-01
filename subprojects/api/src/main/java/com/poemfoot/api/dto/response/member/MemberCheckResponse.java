package com.poemfoot.api.dto.response.member;

public record MemberCheckResponse(
        Boolean isRegistered
) {
    public static MemberCheckResponse from(Boolean isRegistered) {
        return new MemberCheckResponse(isRegistered);
    }
}
