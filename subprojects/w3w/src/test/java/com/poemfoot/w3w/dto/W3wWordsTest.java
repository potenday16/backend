package com.poemfoot.w3w.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class W3wWordsTest {

    @ParameterizedTest
    @CsvSource(value = {"word1,word2,word3"}, delimiter = ',')
    void testToString(String word1, String word2, String word3) {
        // Given
        W3wWords w3wWords = new W3wWords(word1, word2, word3);

        // Expect
        assertAll(
            () -> assertThat(w3wWords).isNotNull(),
            () -> assertThat(w3wWords.toString()).isEqualTo(String.join(".", word1, word2, word3))
        );
    }
}
