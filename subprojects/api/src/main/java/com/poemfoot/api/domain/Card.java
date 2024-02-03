package com.poemfoot.api.domain;

import com.poemfoot.api.domain.member.Member;
import com.poemfoot.api.domain.poem.Poem;
import com.poemfoot.api.dto.request.CardRequest;
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

    private double latitude;

    private double longitude;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "poem_id")
    private Poem poem;

    public Card(
            Member member, Poem poem,
            String font, String fontColor, String background, double latitude, double longitude) {
        this.font = font;
        this.fontColor = fontColor;
        this.background = background;
        this.number = member.plusMaxNumber();
        this.member = member;
        this.poem = poem;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Card(Member member, Poem poem, W3wResult w3wResult,
            CardRequest request) {
        this.member = member;
        this.poem = poem;
        this.number = member.plusMaxNumber();
        this.font = request.font();
        this.fontColor = request.fontColor();
        this.background = request.background();
        this.latitude = w3wResult.getLatitude();
        this.longitude = w3wResult.getLongitude();
    }
}
