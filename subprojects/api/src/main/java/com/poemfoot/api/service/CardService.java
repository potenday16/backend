package com.poemfoot.api.service;

import com.poemfoot.api.domain.Card;
import com.poemfoot.api.domain.W3wResult;
import com.poemfoot.api.domain.Words;
import com.poemfoot.api.domain.member.Member;
import com.poemfoot.api.domain.poem.Poem;
import com.poemfoot.api.dto.request.CardRequest;
import com.poemfoot.api.dto.response.card.CardListResponse;
import com.poemfoot.api.dto.response.card.CardReadyResponse;
import com.poemfoot.api.dto.response.card.CardResponse;
import com.poemfoot.api.exception.notfound.card.NotFoundCardException;
import com.poemfoot.api.exception.notfound.member.NotFoundMemberException;
import com.poemfoot.api.repository.CardRepository;
import com.poemfoot.api.repository.MemberRepository;
import com.poemfoot.api.repository.PoemRepository;
import com.poemfoot.api.repository.W3wResultRepository;
import com.poemfoot.gpt.dto.request.GptChatPoemRequest;
import com.poemfoot.gpt.dto.response.chat.GptChatPoemResponse;
import com.poemfoot.gpt.service.GptPoemProvider;
import com.poemfoot.w3w.W3wProvider;
import com.poemfoot.w3w.dto.W3wWords;
import com.poemfoot.w3w.dto.W3wWordsResponse;
import java.util.List;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class CardService {

    private final CardRepository cardRepository;
    private final MemberRepository memberRepository;
    private final W3wProvider w3wProvider;
    private final GptPoemProvider gptPoemProvider;
    private final PoemService poemService;
    private final W3wService w3wService;

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

    @Transactional
    public CardResponse saveCard(String deviceId, CardRequest request) {
        checkReadiness();

        Member findMember = memberRepository.findFirstByDeviceId(deviceId)
                .orElseThrow(NotFoundMemberException::new);

        Card card = cardRepository.save(new Card(findMember, getPoem(request), request));
        return CardResponse.of(card);
    }

    public CardReadyResponse checkReadiness() {
        boolean isReadiness =
                gptPoemProvider.validateGptRequest() && w3wProvider.validateW3WRequest();
        return CardReadyResponse.from(isReadiness);
    }

    private List<CardResponse> getCards(Long memberId) {
        List<Card> cards = cardRepository.findByMemberId(memberId);

        return cards.stream()
                .map(CardResponse::of)
                .toList();
    }

    private Poem getPoem(CardRequest request) {
        String location = request.location();
        List<String> words = w3wService.requestWords(request.latitude(), request.longitude());

        GptChatPoemResponse poemResponse = poemService.requestPoem(words, location);
        if (!poemResponse.isReuse()) {
            return poemService.savePoem(words, location, poemResponse);
        }
        return poemService.findPoem(words, location);
    }
}
