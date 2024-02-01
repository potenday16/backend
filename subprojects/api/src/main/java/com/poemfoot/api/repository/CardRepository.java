package com.poemfoot.api.repository;

import com.poemfoot.api.domain.Card;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card,Long> {

    List<Card> findByMember(Long memberId);
}
