package com.poemfoot.api.domain.member;

import com.poemfoot.api.exception.badrequest.member.InvalidDeviceOsException;
import java.util.Arrays;
import java.util.Objects;

public enum DeviceOsType {

    IOS("iOS"), ANDROID("Android");

    private String deviceOs;

    DeviceOsType(String deviceOs) {
        this.deviceOs = deviceOs;
    }

    public static DeviceOsType from(String deviceOs) {
        return Arrays.stream(values())
                .filter(it -> Objects.equals(it.deviceOs, deviceOs))
                .findFirst()
                .orElseThrow(InvalidDeviceOsException::new);
    }
}
