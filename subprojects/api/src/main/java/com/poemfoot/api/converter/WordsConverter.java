package com.poemfoot.api.converter;

import com.poemfoot.api.domain.Words;
import com.poemfoot.api.exception.server.poem.FailStringToWordsException;
import com.poemfoot.api.exception.server.poem.FailWordsToStringException;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Arrays;

@Converter(autoApply = true)
public class WordsConverter implements AttributeConverter<Words, String> {
    private static final String WORDS_DELIMITER = ",";

    @Override
    public String convertToDatabaseColumn(Words attribute) {
        if (attribute == null) {
            throw new FailWordsToStringException();
        }
        return String.join(WORDS_DELIMITER, attribute.words());
    }

    @Override
    public Words convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            throw new FailStringToWordsException();
        }
        return Words.of(Arrays.stream(dbData.split(WORDS_DELIMITER)).toList());
    }
}
