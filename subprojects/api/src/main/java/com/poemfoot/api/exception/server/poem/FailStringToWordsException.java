package com.poemfoot.api.exception.server.poem;

import com.poemfoot.api.exception.server.InternetServerErrorException;

public class FailStringToWordsException extends InternetServerErrorException {

    public FailStringToWordsException() {
        super("변환할 수 없는 단어 문자열입니다.", 3501);
    }
}
