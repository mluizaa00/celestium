package com.celestium.server.protocol.packets;

import com.celestium.server.protocol.network.NextState;
import lombok.Data;

/**
 * Information contained on Handshake packet received.
 */
@Data
public class HandshakePacket {

    private final int protocolVersion;
    private final String address;
    private final int port;

    private final NextState state;

}
