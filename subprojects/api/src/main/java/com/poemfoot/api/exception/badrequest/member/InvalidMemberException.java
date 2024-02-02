package com.poemfoot.api.exception.badrequest.member;

import com.poemfoot.api.exception.badrequest.BadRequestException;

public class InvalidMemberException extends BadRequestException {

    public InvalidMemberException() {
        super("이미 등록된 사용자입니다.", 5101);
    }
}
