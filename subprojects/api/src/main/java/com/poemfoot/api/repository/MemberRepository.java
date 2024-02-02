package com.poemfoot.api.repository;

import com.poemfoot.api.domain.member.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findFirstByDeviceId(String deviceId);
}
