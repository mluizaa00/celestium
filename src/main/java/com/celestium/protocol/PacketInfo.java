package com.celestium.protocol;

import lombok.Data;

@Data
public class PacketInfo {

    private final int length;
    private final int packetId;

    private final byte[] data;

}
