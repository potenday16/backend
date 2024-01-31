package com.poemfoot.gpt.exception.badrequest;

import com.poemfoot.gpt.exception.GptException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BadRequestException extends GptException {

    public BadRequestException(String message, int code) {
        super(HttpStatus.BAD_REQUEST, message, code);
    }
}
