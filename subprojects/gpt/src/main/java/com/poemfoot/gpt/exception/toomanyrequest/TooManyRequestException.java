package com.poemfoot.gpt.exception.toomanyrequest;

import com.poemfoot.gpt.exception.GptException;
import org.springframework.http.HttpStatus;

public class TooManyRequestException extends GptException {

    public TooManyRequestException(String message, int code) {
        super(HttpStatus.TOO_MANY_REQUESTS, message, code);
    }
}
