package com.poemfoot.w3w.impl;

import com.poemfoot.w3w.W3wProvider;
import com.what3words.javawrapper.What3WordsV3;
import com.what3words.javawrapper.request.Coordinates;
import com.what3words.javawrapper.response.ConvertTo3WA;
import com.what3words.javawrapper.response.ConvertToCoordinates;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class W3wProviderImpl implements W3wProvider {

    private final What3WordsV3 w3wApi;

    @Override
    public ConvertTo3WA getWords(double latitude, double longitude, Locale locale) {
        return w3wApi.convertTo3wa(new Coordinates(latitude, longitude))
            .language(locale.getLanguage())
            .execute();
    }

    @Override
    public ConvertToCoordinates getCoordinates(String word1, String word2, String word3) {
        String w3wWords = String.join(".", word1, word2, word3);

        return w3wApi.convertToCoordinates(w3wWords)
            .execute();
    }
}
