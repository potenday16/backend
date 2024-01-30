package com.poemfoot.w3w;

import com.what3words.javawrapper.response.ConvertTo3WA;
import com.what3words.javawrapper.response.ConvertToCoordinates;
import java.util.Locale;

public interface W3wProvider {
    ConvertTo3WA getWords(double latitude, double longitude, Locale locale);

    ConvertToCoordinates getCoordinates(String word1, String word2, String word3);
}
