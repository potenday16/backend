package com.poemfoot.api.domain.member.dao;

import com.poemfoot.api.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {

}
