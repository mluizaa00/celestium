package celestium.server.protocol;

import lombok.Data;

@Data
public class PacketInfo {

    private final int length;
    private final int packetId;

    private final byte[] data;

}
