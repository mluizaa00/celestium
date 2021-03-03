package com.celestium.server.protocol;

import lombok.Data;

/**
 * Information contained by a packet.
 */
@Data
public class PacketInfo {

    private final int length;
    private final int packetId;

    private final byte[] data;

}
