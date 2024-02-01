package com.poemfoot.api.domain;

import com.poemfoot.api.domain.member.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Card extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poem_id")
    private Poem poem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "background_id")
    private BackGroundImage backGroundImage;

    public Card(Member member, Poem poem, BackGroundImage backGroundImage) {
        this.member = member;
        setPoem(poem);
        setBackGroundImage(backGroundImage);
    }

    private void setBackGroundImage(BackGroundImage backGroundImage) {
        this.backGroundImage = backGroundImage;
        backGroundImage.getCards().add(this);
    }

    private void setPoem(Poem poem) {
        this.poem = poem;
        poem.getCards().add(this);
    }
}
