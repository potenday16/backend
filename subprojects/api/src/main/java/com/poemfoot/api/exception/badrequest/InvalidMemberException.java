package com.poemfoot.api.exception.badrequest;

public class InvalidMemberException extends BadRequestException {

    public InvalidMemberException() {
        super("이미 등록된 사용자입니다.", 2001);
    }
}
