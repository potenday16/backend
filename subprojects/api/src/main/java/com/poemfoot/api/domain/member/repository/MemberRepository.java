package com.poemfoot.api.domain.member.repository;

import com.poemfoot.api.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {

}
