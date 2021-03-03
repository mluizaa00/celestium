package com.celestium.protocol.packets;

import com.celestium.protocol.enums.NextState;
import lombok.Data;

@Data
public class HandshakePacket {

    private final int protocolVersion;
    private final String address;
    private final int port;

    private final NextState state;

}
