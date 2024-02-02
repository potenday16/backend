package com.poemfoot.api.exception.notfound.member;

import com.poemfoot.api.exception.notfound.NotFoundException;

public class NotFoundMemberException extends NotFoundException {

    public NotFoundMemberException() {
        super("해당 사용자를 찾을 수 없습니다.", 2400);
    }
}
