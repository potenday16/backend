package com.poemfoot.api.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Poem extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "poem_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content", length = 500, nullable = false)
    private String content;

    @Column(name = "words")
    private String words;
    
    @Column(name = "gpt_request_hash")
    private String gptRequestHash;
    
    @OneToMany(mappedBy = "poem")
    private List<Card> cards = new ArrayList<>();

    public Poem(String title, String content, String words, String gptRequestHash) {
        this.title = title;
        this.content = content;
        this.words = words;
        this.gptRequestHash = gptRequestHash;
    }
}
