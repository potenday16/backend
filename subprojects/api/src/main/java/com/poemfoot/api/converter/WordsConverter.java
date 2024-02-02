package com.poemfoot.api.converter;

import com.poemfoot.api.domain.Words;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class WordsConverter implements AttributeConverter<Words, String> {

    @Override
    public String convertToDatabaseColumn(Words attribute) {
        return null;
    }

    @Override
    public Words convertToEntityAttribute(String dbData) {
        return null;
    }
}
