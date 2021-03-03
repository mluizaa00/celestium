package com.celestium.protocol.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NextState {

    STATUS(1),
    LOGIN(2);

    private final int id;

    public static NextState getById(int id) {
        for (NextState state : values()) {
            if (state.getId() == id) return state;
        }

        return null;
    }

}
