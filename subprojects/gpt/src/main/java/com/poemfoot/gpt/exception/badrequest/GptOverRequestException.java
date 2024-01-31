package com.poemfoot.gpt.exception.badrequest;

import com.poemfoot.gpt.exception.GptException;
import org.springframework.http.HttpStatus;

public class GptOverRequestException extends BadRequestException {

    public GptOverRequestException() {
        super("일일 이용 횟수가 초과되었습니다.",1000);
    }
}
