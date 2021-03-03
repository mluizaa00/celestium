package com.celestium.server.protocol.network;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Field received only on Handshake packet, should return STATUS or LOGIN.
 */
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
