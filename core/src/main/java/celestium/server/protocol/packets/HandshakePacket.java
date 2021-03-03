package celestium.server.protocol.packets;

import celestium.server.protocol.enums.NextState;
import lombok.Data;

@Data
public class HandshakePacket {

    private final int protocolVersion;
    private final String address;
    private final int port;

    private final NextState state;

}
