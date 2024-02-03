package com.poemfoot.api.exception.notfound.w3wresult;

import com.poemfoot.api.exception.notfound.NotFoundException;

public class NotFoundW3wResultException extends NotFoundException {

    public NotFoundW3wResultException() {
        super("[W3W] 해당 결과값을 찾을 수 없습니다.", 6400);
    }
}
