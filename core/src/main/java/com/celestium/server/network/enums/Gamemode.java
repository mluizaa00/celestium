package com.celestium.server.network.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents all Minecraft gamemodes, all have a ID.
 */
@Getter
@AllArgsConstructor
public enum Gamemode {

    SURVIVAL(0),
    CREATIVE(1),
    ADVENTURE(2),
    SPECTATOR(3);

    private final int id;

    public static Gamemode getById(int id) {
        for (Gamemode gamemode : values()) {
            if (gamemode.getId() == id) return gamemode;
        }

        return null;
    }

}
