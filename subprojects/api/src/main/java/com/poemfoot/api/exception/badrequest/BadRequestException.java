package com.poemfoot.api.exception.badrequest;

import com.poemfoot.api.exception.ApiException;
import com.poemfoot.gpt.exception.GptException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BadRequestException extends ApiException {

    public BadRequestException(String message, int code) {
        super(HttpStatus.BAD_REQUEST, message, code);
    }
}
