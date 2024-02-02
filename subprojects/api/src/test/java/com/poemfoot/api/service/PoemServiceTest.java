package com.poemfoot.api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.poemfoot.api.domain.poem.PoemAnswer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
@RequiredArgsConstructor
class PoemServiceTest {

    @Test
    @DisplayName("gpt response를 제목과 내용으로 파싱한다.")
    void parsePoemAnswerTest() {
        String inputString = "```json\n{\n  \"제목\": \"중심\",\n  \"내용\": \"다.\\n가다.\"\n}\n```";
        String jsonString = inputString.substring(inputString.indexOf("{"),
                inputString.indexOf("}") + 1);
        System.out.println(jsonString);
        JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();

        System.out.println(jsonObject);
    }
}
