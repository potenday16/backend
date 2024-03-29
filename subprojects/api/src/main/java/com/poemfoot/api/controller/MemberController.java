package com.poemfoot.api.controller;

import static com.poemfoot.api.config.CommonConstants.DEVICE_ID;

import com.poemfoot.api.dto.request.MemberRequest;
import com.poemfoot.api.dto.response.member.MemberCheckResponse;
import com.poemfoot.api.dto.response.member.MemberResponse;
import com.poemfoot.api.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Member", description = "사용자 관련 API")
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "신규 사용자 저장 요청")
    @PostMapping
    public ResponseEntity<MemberResponse> saveMember(
            @Parameter(description = "테스트 유저", example = "test")
            @RequestHeader(DEVICE_ID) String deviceId,
            @RequestBody MemberRequest memberRequest
    ) {
        return ResponseEntity.ok(memberService.saveMember(deviceId, memberRequest));
    }

    @Operation(summary = "등록된 사용자인지 확인 요청")
    @GetMapping("/check")
    public ResponseEntity<MemberCheckResponse> findSavedMember(
            @Parameter(description = "테스트 유저", example = "test")
            @RequestHeader(DEVICE_ID) String deviceId
    ) {
        return ResponseEntity.ok(memberService.findSavedMember(deviceId));
    }
}
