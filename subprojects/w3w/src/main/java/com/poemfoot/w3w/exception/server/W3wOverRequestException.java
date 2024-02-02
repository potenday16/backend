package com.poemfoot.w3w.exception.server;

public class W3wOverRequestException extends InternetServerErrorException {

    public W3wOverRequestException() {
        super("[W3W] 일일 이용 횟수가 초과되었습니다.",2000);
    }
}
