package com.poemfoot.gpt.exception.toomanyrequest;

public class GptTooManyRequestException extends TooManyRequestException {

    public GptTooManyRequestException() {
        super("잠시 후 다시 시도해주세요.",1001);
    }
}
