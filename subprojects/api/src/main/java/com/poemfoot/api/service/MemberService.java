package com.poemfoot.api.service;

import com.poemfoot.api.domain.member.DeviceOsType;
import com.poemfoot.api.domain.member.Member;
import com.poemfoot.api.dto.request.MemberRequest;
import com.poemfoot.api.dto.response.member.MemberCheckResponse;
import com.poemfoot.api.dto.response.member.MemberResponse;
import com.poemfoot.api.exception.badrequest.member.InvalidMemberException;
import com.poemfoot.api.repository.MemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponse saveMember(String deviceId, MemberRequest memberRequest) {
        checkSavedMember(deviceId);

        DeviceOsType deviceOsType = DeviceOsType.from(memberRequest.deviceOsType());
        Member member = new Member(deviceId, deviceOsType, memberRequest.nickname());

        memberRepository.save(member);

        return MemberResponse.of(member.getId(), member.getNickname());
    }

    public MemberCheckResponse findSavedMember(String deviceId){
        Optional<Member> findMember = memberRepository.findFirstByDeviceId(deviceId);
        return MemberCheckResponse.from(findMember.isPresent());
    }

    private void checkSavedMember(String deviceId) {
        Optional<Member> findMember = memberRepository.findFirstByDeviceId(deviceId);
        if (findMember.isPresent()) {
            throw new InvalidMemberException();
        }
    }
}
