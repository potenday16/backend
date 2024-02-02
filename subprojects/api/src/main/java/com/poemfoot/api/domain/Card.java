package com.poemfoot.api.domain;

import com.poemfoot.api.domain.member.Member;
import jakarta.persistence.CascadeType;
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
public class Card extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long id;

    private Long number;

    private String font;

    private String fontColor;

    private String background;

    private String latitude;

    private String longitude;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "poem_id")
    private Poem poem;

    public Card(
            Member member, Poem poem,
            String font, String fontColor, String background, String latitude, String longitude) {
        this.font = font;
        this.fontColor = fontColor;
        this.background = background;
        this.number = member.plusMaxNumber();
        this.member = member;
        this.poem = poem;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
