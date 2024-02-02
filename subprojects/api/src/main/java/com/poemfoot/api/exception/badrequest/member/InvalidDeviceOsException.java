package com.poemfoot.api.exception.badrequest.member;

import com.poemfoot.api.exception.badrequest.BadRequestException;
import lombok.Getter;

@Getter
public class InvalidDeviceOsException extends BadRequestException {

    public InvalidDeviceOsException() {
        super("잘못된 Device OS 정보입니다.",2102);
    }
}
