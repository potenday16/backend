package com.poemfoot.api.domain;

import java.util.List;


public record Words(
        List<String> words
) {
    public static Words of(List<String> words) {
        return new Words(words);
    }
}
