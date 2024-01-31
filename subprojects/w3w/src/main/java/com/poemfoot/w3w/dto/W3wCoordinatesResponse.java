package com.poemfoot.w3w.dto;

import com.what3words.javawrapper.response.ConvertToCoordinates;
import com.what3words.javawrapper.response.Coordinates;
import com.what3words.javawrapper.response.Square;

public record W3wCoordinatesResponse(
    String country,
    Square square,
    String nearestPlace,
    Coordinates coordinates,
    W3wWords words,
    String language,
    String locale,
    String map
){
    public static W3wCoordinatesResponse of(ConvertToCoordinates convertToCoordinates) {
        return new W3wCoordinatesResponse(
            convertToCoordinates.getCountry(),
            convertToCoordinates.getSquare(),
            convertToCoordinates.getNearestPlace(),
            convertToCoordinates.getCoordinates(),
            W3wWords.of(convertToCoordinates.getWords()),
            convertToCoordinates.getLanguage(),
            convertToCoordinates.getLocale(),
            convertToCoordinates.getMap()
        );
    }
}
