package com.poemfoot.api.exception.notfound.card;

import com.poemfoot.api.exception.notfound.NotFoundException;

public class NotFoundCardException extends NotFoundException {

    public NotFoundCardException() {
        super("해당 카드를 찾을 수 없습니다.", 3400);
    }
}
