package com.poemfoot.w3w.exception.toomanyrequest;

import com.poemfoot.w3w.exception.W3WException;
import org.springframework.http.HttpStatus;

public class TooManyRequestException extends W3WException {

    public TooManyRequestException(String message, int code) {
        super(HttpStatus.TOO_MANY_REQUESTS, message, code);
    }
}
