package com.poemfoot.w3w.dto;

public record W3wWords (
    String word1,
    String word2,
    String word3
){
    public static W3wWords of(String compositeWords) {
        String[] words = compositeWords.split("\\.");

        if (words.length != 3) {
            throw new IllegalArgumentException("Invalid composite words: " + compositeWords);
        }

        return new W3wWords(words[0], words[1], words[2]);
    }

    @Override
    public String toString() {
        return String.join(".", word1, word2, word3);
    }
}
