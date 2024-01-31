package com.poemfoot.gpt.domain.answer;

import com.poemfoot.gpt.domain.BaseTime;
import com.poemfoot.gpt.dto.response.chat.GptChatPoemResponse;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GptAnswer extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gpt_answer", length = 500, nullable = false)
    private String answer;

    private String object;

    private String model;

    public GptAnswer(String answer, String object, String model) {
        this.answer = answer;
        this.object = object;
        this.model = model;
    }


}
