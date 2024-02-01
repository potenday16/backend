package com.poemfoot.api.exception.notfound;

import com.poemfoot.api.exception.ApiException;
import org.springframework.http.HttpStatus;

public class NotFoundException extends ApiException {

    public NotFoundException(String message, int code) {
        super(HttpStatus.NOT_FOUND, message, code);
    }
}
