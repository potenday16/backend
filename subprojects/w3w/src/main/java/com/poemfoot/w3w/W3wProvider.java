package com.poemfoot.w3w;

import com.poemfoot.w3w.dto.W3wCoordinatesResponse;
import com.poemfoot.w3w.dto.W3wWordsResponse;
import java.util.Locale;

public interface W3wProvider {
    W3wWordsResponse getWords(double latitude, double longitude, Locale locale);

    W3wCoordinatesResponse getCoordinates(String word1, String word2, String word3);
}
