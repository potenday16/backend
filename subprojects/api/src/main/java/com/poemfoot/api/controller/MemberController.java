package com.poemfoot.api.controller;

import static com.poemfoot.api.config.CommonConfig.DEVICE_ID;

import com.poemfoot.api.dto.request.MemberRequest;
import com.poemfoot.api.dto.response.member.MemberCheckResponse;
import com.poemfoot.api.dto.response.member.MemberResponse;
import com.poemfoot.api.service.MemberService;
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
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberResponse> saveMember(
            @RequestHeader(DEVICE_ID) String deviceId,
            @RequestBody MemberRequest memberRequest
    ) {
        return ResponseEntity.ok(memberService.saveMember(deviceId, memberRequest));
    }

    @GetMapping("/check")
    public ResponseEntity<MemberCheckResponse> findSavedMember(
            @RequestHeader(DEVICE_ID) String deviceId
    ){
        return ResponseEntity.ok(memberService.findSavedMember(deviceId));
    }
}
