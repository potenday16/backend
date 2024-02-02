package com.poemfoot.api.domain.poem;

import com.poemfoot.api.converter.WordsConverter;
import com.poemfoot.api.domain.BaseTime;
import com.poemfoot.api.domain.Words;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Poem extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "poem_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content", length = 500, nullable = false)
    private String content;

    @Convert(converter = WordsConverter.class)
    private Words words;

    private String location;
    
    private String gptRequestHash;

    public Poem(String title, String content, Words words, String location, String gptRequestHash) {
        this.title = title;
        this.content = content;
        this.words = words;
        this.location = location;
        this.gptRequestHash = gptRequestHash;
    }
}
