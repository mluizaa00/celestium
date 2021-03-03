package com.celestium.server.network.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents all Minecraft default dimensions, all have a ID.
 */
@Getter
@AllArgsConstructor
public enum Dimension {

    NETHER(-1),
    OVERWORLD(0),
    END(1);

    private final int id;

    public static Dimension getById(int id) {
        for (Dimension dimension : values()) {
            if (dimension.getId() == id) return dimension;
        }

        return null;
    }

}
