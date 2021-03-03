package com.celestium.server.protocol.network;

import lombok.Getter;

/**
 * Direction sent in packets.
 *
 * SERVER_BOUND indicates that the packet is sent by the server.
 * CLIENT_BOUND indicated that the packet is sent by the player client.
 */
@Getter
public enum ProtocolDirection {

    SERVER_BOUND,
    CLIENT_BOUND

}
