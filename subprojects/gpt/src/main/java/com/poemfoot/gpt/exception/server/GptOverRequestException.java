package com.poemfoot.gpt.exception.server;

public class GptOverRequestException extends InternetServerErrorException {

    public GptOverRequestException() {
        super("일일 이용 횟수가 초과되었습니다.",1000);
    }
}
