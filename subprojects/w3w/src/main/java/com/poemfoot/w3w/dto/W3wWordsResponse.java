package com.poemfoot.w3w.dto;

import com.what3words.javawrapper.response.ConvertTo3WA;
import com.what3words.javawrapper.response.Coordinates;
import com.what3words.javawrapper.response.Square;

public record W3wWordsResponse(
    String country,
    Square square,
    String nearestPlace,
    Coordinates coordinates,
    W3wWords words,
    String language,
    String locale,
    String map
){
    public static W3wWordsResponse of(ConvertTo3WA convertTo3WA) {
        return new W3wWordsResponse(
            convertTo3WA.getCountry(),
            convertTo3WA.getSquare(),
            convertTo3WA.getNearestPlace(),
            convertTo3WA.getCoordinates(),
            W3wWords.of(convertTo3WA.getWords()),
            convertTo3WA.getLanguage(),
            convertTo3WA.getLocale(),
            convertTo3WA.getMap()
        );
    }
}
