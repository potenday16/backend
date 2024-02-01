package com.poemfoot.api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.poemfoot.api.domain.member.DeviceOsType;
import com.poemfoot.api.domain.member.Member;
import com.poemfoot.api.dto.request.MemberRequest;
import com.poemfoot.api.dto.response.member.MemberResponse;
import com.poemfoot.api.exception.notfound.member.NotFoundMemberException;
import com.poemfoot.api.repository.MemberRepository;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    String deviceId;
    String nickname;
    String deviceOs;

    @BeforeEach
    void beforeEach() {
        deviceId = "HDFA3dfd23";
        nickname = "test";
        deviceOs = "iOS";
    }

    @Test
    @DisplayName("사용자를 저장한다.")
    void saveMemberTest() {
        MemberRequest memberRequest = new MemberRequest(nickname, deviceOs);
        MemberResponse memberResponse = memberService.saveMember(deviceId, memberRequest);

        Member member = memberRepository.findById(memberResponse.id())
                .orElseThrow(NotFoundMemberException::new);
        List<Member> members = memberRepository.findAll();

        assertThat(member.getDeviceId()).isEqualTo(deviceId);
        assertThat(member.getNickname()).isEqualTo(memberRequest.nickname());
        assertThat(member.getDeviceOs()).isEqualTo(DeviceOsType.IOS);
        assertThat(members).hasSize(1);
    }

    @Test
    @DisplayName("이미 등록된 사용자인 경우 true 를 반환한다.")
    void checkSavedMemberTest() {
        MemberRequest memberRequest = new MemberRequest(nickname, deviceOs);
        MemberResponse memberResponse = memberService.saveMember(deviceId, memberRequest);

        assertThat(memberService.findSavedMember(deviceId).isRegistered()).isTrue();
    }

    @Test
    @DisplayName("처음 등록 사용자인 경우 false 를 반환한다.")
    void checkEnterMemberTest() {
        assertThat(memberService.findSavedMember(deviceId).isRegistered()).isFalse();
    }
}
