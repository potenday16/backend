package com.poemfoot.api.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.poemfoot.api.domain.member.DeviceOsType;
import com.poemfoot.api.domain.member.Member;
import com.poemfoot.api.dto.request.MemberRequest;
import com.poemfoot.api.dto.response.member.MemberResponse;
import com.poemfoot.api.exception.notfound.member.NotFoundMemberException;
import com.poemfoot.api.repository.MemberRepository;
import java.util.List;
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
    String deviceIOS;
    String deviceAndroid;

    @BeforeEach
    void beforeEach() {
        deviceId = "HDFA3dfd23";
        nickname = "test";
        deviceIOS = "iOS";
        deviceAndroid = "Android";
    }

    @Test
    @DisplayName("사용자를 저장한다.")
    void saveMemberTest() {
        MemberRequest memberRequest = new MemberRequest(nickname, deviceIOS);
        int beforeMemberSize = memberRepository.findAll().size();
        MemberResponse memberResponse = memberService.saveMember(deviceId, memberRequest);

        Member member = memberRepository.findById(memberResponse.id())
                .orElseThrow(NotFoundMemberException::new);
        List<Member> members = memberRepository.findAll();

        assertThat(member.getDeviceId()).isEqualTo(deviceId);
        assertThat(member.getNickname()).isEqualTo(memberRequest.nickname());
        assertThat(member.getDeviceOs()).isEqualTo(DeviceOsType.IOS);
        assertThat(members).hasSize(beforeMemberSize + 1);
    }

    @Test
    @DisplayName("사용자 기기 os를 정확하게 등록한다.")
    void saveMemberDeviceOsTest() {
        String androidDeviceId = "test123";
        MemberRequest IOSRequest = new MemberRequest(nickname, deviceIOS);
        MemberRequest AndroidRequest = new MemberRequest(nickname, deviceAndroid);
        MemberResponse IOSResponse = memberService.saveMember(deviceId, IOSRequest);
        MemberResponse AndroidResponse = memberService.saveMember(androidDeviceId, AndroidRequest);

        Member iOSMember = memberRepository.findById(IOSResponse.id())
                .orElseThrow(NotFoundMemberException::new);
        Member androidMember = memberRepository.findById(AndroidResponse.id())
                .orElseThrow(NotFoundMemberException::new);

        assertThat(iOSMember.getDeviceOs()).isEqualTo(DeviceOsType.IOS);
        assertThat(androidMember.getDeviceOs()).isEqualTo(DeviceOsType.ANDROID);
    }

    @Test
    @DisplayName("이미 등록된 사용자인 경우 true 를 반환한다.")
    void checkSavedMemberTest() {
        MemberRequest memberRequest = new MemberRequest(nickname, deviceIOS);
        MemberResponse memberResponse = memberService.saveMember(deviceId, memberRequest);

        assertThat(memberService.findSavedMember(deviceId).isRegistered()).isTrue();
    }

    @Test
    @DisplayName("처음 등록 사용자인 경우 false 를 반환한다.")
    void checkEnterMemberTest() {
        assertThat(memberService.findSavedMember(deviceId).isRegistered()).isFalse();
    }
}
