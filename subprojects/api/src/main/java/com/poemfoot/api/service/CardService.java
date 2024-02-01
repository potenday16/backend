package com.poemfoot.api.service;

import com.poemfoot.api.domain.Card;
import com.poemfoot.api.domain.member.Member;
import com.poemfoot.api.dto.response.card.CardListResponse;
import com.poemfoot.api.dto.response.card.CardResponse;
import com.poemfoot.api.exception.notfound.card.NotFoundCardException;
import com.poemfoot.api.exception.notfound.member.NotFoundMemberException;
import com.poemfoot.api.repository.CardRepository;
import com.poemfoot.api.repository.MemberRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final MemberRepository memberRepository;

    public CardResponse findCard(Long cardId) {
        Card findCard = cardRepository.findById(cardId)
                .orElseThrow(NotFoundCardException::new);

        return CardResponse.of(findCard);
    }

    public CardListResponse findCards(String deviceId) {
        Member findMember = memberRepository.findFirstByDeviceId(deviceId)
                .orElseThrow(NotFoundMemberException::new);

        return CardListResponse.of(getCards(findMember.getId()));
    }

    private List<CardResponse> getCards(Long memberId) {
        List<Card> cards = cardRepository.findByMemberId(memberId);

        return cards.stream()
                .map(CardResponse::of)
                .toList();
    }
}