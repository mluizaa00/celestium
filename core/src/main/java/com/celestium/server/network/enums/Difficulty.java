package com.celestium.server.network.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents the difficulty of the Minecraft server, each one has a ID.
 */
@Getter
@AllArgsConstructor
public enum Difficulty {

    PEACEFUL(0),
    EASY(1),
    NORMAL(2),
    HARD(3);

    private final int id;

    public static Difficulty getById(int id) {
        for (Difficulty difficulty : values()) {
            if (difficulty.getId() == id) return difficulty;
        }

        return null;
    }

}
