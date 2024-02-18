package com.poemfoot.w3w.impl;

import static com.poemfoot.w3w.config.W3wConfig.MAX_TOTAL_REQUEST_COUNT;
import static com.poemfoot.w3w.config.W3wConfig.totalRequestCount;

import com.poemfoot.w3w.W3wProvider;
import com.poemfoot.w3w.dto.W3wCoordinatesResponse;
import com.poemfoot.w3w.dto.W3wWordsResponse;
import com.poemfoot.w3w.exception.server.W3wOverRequestException;
import com.what3words.javawrapper.What3WordsV3;
import com.what3words.javawrapper.request.Coordinates;
import com.what3words.javawrapper.response.ConvertTo3WA;
import com.what3words.javawrapper.response.ConvertToCoordinates;
import java.util.Locale;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class W3wProviderImpl implements W3wProvider {

    private final What3WordsV3 w3wApi;

    @Override
    public W3wWordsResponse getWords(double latitude, double longitude, Locale locale) {
        validateW3WRequest();

        ConvertTo3WA convertTo3WA = w3wApi.convertTo3wa(new Coordinates(latitude, longitude))
            .language(locale.getLanguage())
            .execute();

        w3wRequestCountUp();
        return W3wWordsResponse.of(convertTo3WA);
    }

    @Override
    public W3wCoordinatesResponse getCoordinates(String word1, String word2, String word3) {
        validateW3WRequest();

        String w3wWords = String.join(".", word1, word2, word3);

        ConvertToCoordinates convertToCoordinates = w3wApi.convertToCoordinates(w3wWords)
            .execute();

        w3wRequestCountUp();
        return W3wCoordinatesResponse.of(convertToCoordinates);
    }

    @Override
    public boolean validateW3WRequest() {
        if (totalRequestCount.get() >= MAX_TOTAL_REQUEST_COUNT) {
            throw new W3wOverRequestException();
        }
        return true;
    }

    private boolean w3wRequestCountUp() {
        int currentCount = totalRequestCount.get();
        return totalRequestCount.compareAndSet(currentCount, currentCount + 1);
    }
}
