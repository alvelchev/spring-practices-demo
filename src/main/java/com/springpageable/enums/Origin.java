package com.springpageable.enums;

import lombok.Getter;

import java.util.Arrays;

public enum Origin {
    PM_MANUAL_UPLOAD(0),
    PM_AUTOMATIC_UPLOAD(1),
    CONNECTOR(2),
    IOT_HUB_MANUAL(3),
    DPS_NOT_CONNECTED(4),
    DEFAULT(-1);

    private static final Origin[] values = values();

    @Getter
    private final int value;

    Origin(int value) {
        this.value = value;
    }

    public static Origin getByValue(int value) {
        return Arrays.stream(values).filter(e -> e.value == value).findFirst().orElse(DEFAULT);
    }
}
