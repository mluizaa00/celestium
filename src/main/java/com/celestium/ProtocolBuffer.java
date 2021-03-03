package com.celestium;

import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProtocolBuffer {

    private static ByteBuf byteBuf;

    public static int readVarInt() {
        int number = 0;
        int result = 0;

        byte read;
        do {
            read = readByte();
            number |= (read & 127) << result++ * 7;
            
            if (result > 5) throw new RuntimeException("VarInt is too big");
        } while ((read & 128) == 128);

        return number;
    }

    public static long readVarLong() {
        int number = 0;
        long result = 0;

        byte read;
        do {
            read = byteBuf.readByte();
            
            long value = (read & 0b01111111);
            result |= (value << (7 * number));

            number++;
            if (number > 10) throw new RuntimeException("VarLong is too big");
            
        } while ((read & 0b10000000) != 0);

        return result;
    }

    public static void writeVarInt(int value) {
        do {
            byte varInt = (byte) (value & 0b01111111);
            
            value >>>= 7;
            if (value != 0) varInt |= 0b10000000;
            
            byteBuf.writeByte(varInt);
        } while (value != 0);
    }

    public static void writeVarLong(long value) {
        do {
            byte varLong = (byte) (value & 0b01111111);
            value >>>= 7;
            if (value != 0) varLong |= 0b10000000;
            
            byteBuf.writeByte(varLong);
        } while (value != 0);
    }

    public static byte readByte() {
        return byteBuf.readByte();
    }

}
