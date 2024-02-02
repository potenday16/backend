package com.poemfoot.api.exception.server;

import com.poemfoot.api.exception.ApiException;
import org.springframework.http.HttpStatus;

public class InternetServerErrorException extends ApiException {

    public InternetServerErrorException(String message, int code) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message, code);
    }
}
