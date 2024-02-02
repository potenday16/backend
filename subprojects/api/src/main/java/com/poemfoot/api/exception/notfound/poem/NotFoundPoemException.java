package com.poemfoot.api.exception.notfound.poem;

import com.poemfoot.api.exception.notfound.NotFoundException;

public class NotFoundPoemException extends NotFoundException {

    public NotFoundPoemException() {
        super("해당 시를 찾을 수 없습니다.", 3400);
    }
}
