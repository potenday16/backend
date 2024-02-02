package com.poemfoot.w3w.exception.server;

import com.poemfoot.w3w.exception.W3wException;
import org.springframework.http.HttpStatus;

public class InternetServerErrorException extends W3wException {

    public InternetServerErrorException(String message, int code) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message, code);
    }
}
