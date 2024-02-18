package com.poemfoot.w3w.impl;

import static com.poemfoot.w3w.config.W3wConfig.MAX_TOTAL_REQUEST_COUNT;
import static com.poemfoot.w3w.config.W3wConfig.totalRequestCount;

import com.poemfoot.w3w.W3wProvider;
import com.poemfoot.w3w.dto.W3wCoordinatesResponse;
import com.poemfoot.w3w.dto.W3wWordsResponse;
import com.poemfoot.w3w.exception.server.W3wOverRequestException;
import com.what3words.javawrapper.What3WordsV3;
import com.what3words.javawrapper.response.ConvertTo3WA;
import com.what3words.javawrapper.response.ConvertToCoordinates;
import com.what3words.javawrapper.response.Coordinates;
import com.what3words.javawrapper.response.Square;
import java.util.Locale;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MockW3wProviderImpl implements W3wProvider {

    private final What3WordsV3 w3wApi;

    @Override
    public W3wWordsResponse getWords(double latitude, double longitude, Locale locale) {
        ConvertTo3WA convertTo3WA = new ConvertTo3WA("KR",
                new Square(37.543149,127.081497,37.543122,127.081463),
                "서울특별시",
                new Coordinates(37.543136,127.08148),
                "보일러.소녀.국물",
                "ko",
                "https://w3w.co/%EB%B3%B4%EC%9D%BC%EB%9F%AC.%EC%86%8C%EB%85%80.%EA%B5%AD%EB%AC%BC");

        return W3wWordsResponse.of(convertTo3WA);
    }

    @Override
    public W3wCoordinatesResponse getCoordinates(String word1, String word2, String word3) {
        ConvertToCoordinates convertToCoordinates = new ConvertToCoordinates("KR",
                new Square(37.543149,127.081497,37.543122,127.081463),
                "서울특별시",
                new Coordinates(37.543136,127.08148),
                "보일러.소녀.국물",
                "ko",
                "https://w3w.co/%EB%B3%B4%EC%9D%BC%EB%9F%AC.%EC%86%8C%EB%85%80.%EA%B5%AD%EB%AC%BC");

        return W3wCoordinatesResponse.of(convertToCoordinates);
    }

    @Override
    public boolean validateW3WRequest() {
        if (totalRequestCount.get() >= MAX_TOTAL_REQUEST_COUNT) {
            throw new W3wOverRequestException();
        }
        return true;
    }
}
