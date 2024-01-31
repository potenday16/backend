package com.poemfoot.w3w;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.poemfoot.w3w.dto.W3wCoordinatesResponse;
import com.poemfoot.w3w.dto.W3wWords;
import com.poemfoot.w3w.dto.W3wWordsResponse;
import java.util.Locale;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled
@SpringBootTest
class W3wProviderTest {
    @Autowired
    private W3wProvider w3wProvider;

    @ParameterizedTest(name = "getWords({0}, {1}, {2})")
    @CsvSource(value = {"37.2,127.5,ko"}, delimiter = ',')
    void getWords(double latitude, double longitude, Locale locale) {
        // When
        W3wWordsResponse result = w3wProvider.getWords(latitude, longitude, locale);

        // Then
        assertAll(
            () -> assertThat(result).isNotNull(),
            () -> assertThat(result).hasFieldOrProperty("words")
        );

        W3wWords w3wWords = result.words();

        assertAll(
            () -> assertThat(w3wWords).isNotNull(),
            () -> assertThat(w3wWords).hasNoNullFieldsOrProperties()
        );

        assertAll(
            () -> assertThat(w3wWords.word1()).isNotBlank(),
            () -> assertThat(w3wWords.word2()).isNotBlank(),
            () -> assertThat(w3wWords.word3()).isNotBlank()
        );
    }

    @ParameterizedTest
    @CsvSource(value = {"좋겠다.휴게실.이성"}, delimiter = '.')
    void getCoordinates(String word1, String word2, String word3) {
        W3wCoordinatesResponse result = w3wProvider.getCoordinates(word1, word2, word3);

        assertAll(
            () -> assertThat(result).isNotNull(),
            () -> assertThat(result).hasFieldOrProperty("coordinates")
        );

        assertAll(
            () -> assertThat(result.coordinates()).isNotNull(),
            () -> assertThat(result.coordinates()).hasFieldOrProperty("lat"),
            () -> assertThat(result.coordinates()).hasFieldOrProperty("lng")
        );
    }
}
