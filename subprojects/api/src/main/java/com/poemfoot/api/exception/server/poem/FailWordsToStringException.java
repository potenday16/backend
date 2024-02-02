package com.poemfoot.api.exception.server.poem;

import com.poemfoot.api.exception.server.InternetServerErrorException;

public class FailWordsToStringException extends InternetServerErrorException {

    public FailWordsToStringException() {
        super("변환할 수 없는 단어 형식입니다.", 3500);
    }
}
