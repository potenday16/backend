package com.poemfoot.api.dto.request;

import com.poemfoot.api.domain.member.DeviceOsType;

public record MemberRequest(
        String nickname,
        String deviceOsType
) { }
