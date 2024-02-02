package com.poemfoot.gpt.exception.server;

import com.poemfoot.gpt.exception.GptException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InternetServerErrorException extends GptException {

    public InternetServerErrorException(String message, int code) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message, code);
    }
}
