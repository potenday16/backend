package com.poemfoot.w3w.exception.toomanyrequest;

public class W3WTooManyRequestException extends TooManyRequestException {

    public W3WTooManyRequestException() {
        super("[W3W] 잠시 후 다시 시도해주세요.",2001);
    }
}
