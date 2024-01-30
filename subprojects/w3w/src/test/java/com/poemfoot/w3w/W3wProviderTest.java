package com.poemfoot.w3w;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.what3words.javawrapper.response.ConvertTo3WA;
import com.what3words.javawrapper.response.ConvertToCoordinates;
import java.util.Locale;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled
@SpringBootTest
@SpringBootApplication
class W3wProviderTest {
    @Autowired
    private W3wProvider w3wProvider;

    @ParameterizedTest(name = "getWords({0}, {1}, {2})")
    @CsvSource(value = {"37.2,127.5,ko"}, delimiter = ',')
    void getWords(double latitude, double longitude, Locale locale) {
        // When
        ConvertTo3WA result = w3wProvider.getWords(latitude, longitude, locale);

        // Then
        assertAll(
            () -> assertThat(result).isNotNull(),
            () -> assertThat(result).hasFieldOrProperty("words")
        );

        String w3wWords = result.getWords();

        assertAll(
            () -> assertThat(w3wWords).isNotNull(),
            () -> assertThat(w3wWords).isNotBlank()
        );

        String[] words = w3wWords.split("\\.");

        assertAll(
            () -> assertThat(words).isNotNull(),
            () -> assertThat(words).hasSize(3),
            () -> assertThat(words).allMatch(Strings::isNotBlank)
        );
    }

    @ParameterizedTest
    @CsvSource(value = {"좋겠다.휴게실.이성"}, delimiter = '.')
    void getCoordinates(String word1, String word2, String word3) {
        ConvertToCoordinates coordinates = w3wProvider.getCoordinates(word1, word2, word3);

        assertAll(
            () -> assertThat(coordinates).isNotNull(),
            () -> assertThat(coordinates).hasFieldOrProperty("coordinates")
        );

        assertAll(
            () -> assertThat(coordinates.getCoordinates()).isNotNull(),
            () -> assertThat(coordinates.getCoordinates()).hasFieldOrProperty("lat"),
            () -> assertThat(coordinates.getCoordinates()).hasFieldOrProperty("lng")
        );
    }
}
