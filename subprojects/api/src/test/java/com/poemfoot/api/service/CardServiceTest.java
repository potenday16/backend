package com.poemfoot.api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.poemfoot.api.domain.Card;
import com.poemfoot.api.domain.poem.Poem;
import com.poemfoot.api.domain.Words;
import com.poemfoot.api.domain.member.DeviceOsType;
import com.poemfoot.api.domain.member.Member;
import com.poemfoot.api.dto.response.card.CardListResponse;
import com.poemfoot.api.dto.response.card.CardResponse;
import com.poemfoot.api.exception.notfound.card.NotFoundCardException;
import com.poemfoot.api.exception.notfound.member.NotFoundMemberException;
import com.poemfoot.api.repository.CardRepository;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
    String font;
    String fontColor;
    String background;
    double latitude;
    double longitude;
    private Member member;
    private Poem poem;

    @BeforeEach
    void beforeEach() {
        deviceId = "HDFA3dfd23";
        nickname = "test";
        member = new Member(deviceId, DeviceOsType.IOS, nickname);
        font = "sans-serif";
        fontColor = "black";
        background = "white";
        latitude = 37.5475276;
        longitude = 126.978611;

        Words words = new Words(List.of("word1", "word2", "word3"));
        poem = new Poem("title", "content", words, "gptRequestHash");
    }

    @Test
    @DisplayName("동일한 id를 가진 card에 대해서는 동일한 카드를 조회한다.")
    void findCardTest() {
        Card card = new Card(member, poem, font, fontColor, background, latitude, longitude);
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
        Card card = new Card(member, poem, font, fontColor, background, latitude, longitude);
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

    @Test
    @DisplayName("카드를 생성한다.")
    void saveCardTest() {
        Card card = new Card(member, poem, font, fontColor, background, latitude, longitude);
        Card savedCard = cardRepository.save(card);

        Card findCard = cardRepository.findById(savedCard.getId())
                .orElseThrow(NotFoundCardException::new);

        assertThat(findCard.getId()).isEqualTo(savedCard.getId());
        assertThat(findCard.getNumber()).isEqualTo(savedCard.getNumber());
        assertThat(findCard.getPoem().getContent()).isEqualTo(savedCard.getPoem().getContent());
        assertThat(findCard.getMember().getDeviceId()).isEqualTo(savedCard.getMember().getDeviceId());
        assertThat(findCard.getFont()).isEqualTo(savedCard.getFont());
        assertThat(findCard.getFontColor()).isEqualTo(savedCard.getFontColor());
        assertThat(findCard.getBackground()).isEqualTo(savedCard.getBackground());
        assertThat(findCard.getPoem().getContent()).isEqualTo(savedCard.getPoem().getContent());
    }

    @Test
    @DisplayName("카드 번호를 현재 사용자의 maxNumber+1로 증가시킨다.")
    void plusCardNumberTest() {
        Long beforeMaxNumber = member.getMaxNumber();
        Long afterMaxNumber = beforeMaxNumber + 1;

        Card card = new Card(member, poem, font, fontColor, background, latitude, longitude);
        Card savedCard = cardRepository.save(card);

        assertThat(savedCard.getNumber()).isEqualTo(afterMaxNumber);
        assertThat(member.getMaxNumber()).isEqualTo(afterMaxNumber);
    }

    @Test
    @DisplayName("카드 생성이 유효한 경우 카드를 생성한다.")
    void checkValidCreateCardTest() {
        assertThat(cardService.checkReadiness().isReady()).isTrue();
    }

    @Test
    @DisplayName("카드 생성이 유효하지 않은 경우 예외처리한다.")
    void checkNotValidCreateCardTest() {
        //TODO
    }
}
