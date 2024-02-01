package com.poemfoot.api.exception.badrequest;

import lombok.Getter;

@Getter
public class InvalidDeviceOsException extends BadRequestException {

    public InvalidDeviceOsException() {
        super("잘못된 Device OS 정보입니다.",2000);
    }
}
