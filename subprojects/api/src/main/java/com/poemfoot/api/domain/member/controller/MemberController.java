package com.poemfoot.api.domain.member.controller;

import com.poemfoot.api.domain.member.repository.MemberRepository;
import com.poemfoot.api.domain.member.domain.Member;
import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberRepository memberRepository;

    @GetMapping
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @PostConstruct
    public void init() {
        memberRepository.save(new Member("poemfoot1"));
        memberRepository.save(new Member("poemfoot2"));
        memberRepository.save(new Member("poemfoot3"));
    }
}
