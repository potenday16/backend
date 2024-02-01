package com.poemfoot.api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.poemfoot.api.domain.BackGroundImage;
import com.poemfoot.api.domain.Card;
import com.poemfoot.api.domain.Poem;
import com.poemfoot.api.domain.member.DeviceOsType;
import com.poemfoot.api.domain.member.Member;
import com.poemfoot.api.dto.response.card.CardListResponse;
import com.poemfoot.api.dto.response.card.CardResponse;
import com.poemfoot.api.exception.notfound.card.NotFoundCardException;
import com.poemfoot.api.exception.notfound.member.NotFoundMemberException;
import com.poemfoot.api.repository.CardRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class CardServiceTest {

    @Autowired
    CardService cardService;
    @Autowired
    CardRepository cardRepository;

    String deviceId;
    String nickname;
    private Member member;
    private Poem poem;
    private BackGroundImage backGroundImage;

    @BeforeEach
    void beforeEach() {
        deviceId = "HDFA3dfd23";
        nickname = "test";
        member = new Member(deviceId, DeviceOsType.IOS, nickname);
        poem = new Poem("title", "content", "words", "gptRequestHash");
        backGroundImage = new BackGroundImage();
    }

    @Test
    @DisplayName("동일한 id를 가진 card에 대해서는 동일한 카드를 조회한다.")
    void findCardTest() {
        Card card = new Card(member, poem, backGroundImage);
        Card savedCard = cardRepository.save(card);

        CardResponse cardResponse = cardService.findCard(savedCard.getId());

        assertThat(cardResponse.id()).isEqualTo(card.getId());
        assertThat(cardResponse.content()).isEqualTo(card.getPoem().getContent());
    }

    @Test
    @DisplayName("해당 id를 가진 카드가 없다면 예외 처리한다.")
    void notFoundCardTest() {
        assertThrowsExactly(NotFoundCardException.class,
                () -> cardService.findCard(10L));
    }

    @Test
    @DisplayName("특정 사용자가 저장한 카드를 조회한다.")
    void findCardsTest(){
        Card card = new Card(member, poem, backGroundImage);
        Card savedCard = cardRepository.save(card);

        CardListResponse cardListResponse = cardService.findCards(deviceId);

        assertThat(cardListResponse.cards()).hasSize(1);
    }

    @Test
    @DisplayName("일기 전체 조회 시, 특정 DeviceId가 존재하지 않는 경우 예외처리한다.")
    void notFoundMemberTest() {
        assertThrowsExactly(NotFoundMemberException.class,
                () -> cardService.findCards("test"));
    }
}
