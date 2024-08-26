package net.themade4.bs4j.utils;

public class BinaryPacker {

    public static byte writeBool(boolean value) {
        return (byte) (value ? 0x01 : 0x00);
    }

    public static boolean readBool(byte bytes) {
        return ((int) bytes) == 1;
    }

    public static byte writeInt8(char bytes) {
        return (byte) bytes;
    }

    public static char readInt8(byte bytes) {
        return (char) (bytes & 0xFF);
    }

    public static byte[] writeInt16(short value) {
        return new byte[]{
                (byte) ((value >>> 8) & 0xFF),
                (byte) (value & 0xFF)
        };
    }

    public static short readInt16(byte[] bytes) {
        return (short) (((bytes[0] & 0xFF) << 8) + (bytes[1] & 0xFF));
    }

    public static byte[] writeUInt16(short value) {
        value &= (short) 0xffff;
        return new byte[]{
                (byte) (value & 0xFF),
                (byte) ((value >>> 8) & 0xFF)
        };
    }

    public static short readUInt16(byte[] bytes) {
        return (short) (((bytes[1] & 0xFF) << 8) + (bytes[0] & 0xFF));
    }

    public static byte[] writeInt24(int value) {
        return new byte[]{
                (byte) ((value >>> 16) & 0xFF),
                (byte) ((value >>> 8) & 0xFF),
                (byte) (value & 0xFF)
        };
    }

    public static int readInt24(byte[] bytes) {
        return readInt32(new byte[]{
                (byte) 0x00,
                bytes[0],
                bytes[1],
                bytes[2]
        });
    }

    public static byte[] writeUInt24(int value) {
        return new byte[]{
                (byte) (value & 0xFF),
                (byte) ((value >>> 8) & 0xFF),
                (byte) ((value >>> 16) & 0xFF)
        };
    }

    public static int readUInt24(byte[] bytes) {
        return readUInt32(new byte[]{
                bytes[0],
                bytes[1],
                bytes[2],
                (byte) 0x00
        });
    }

    public static byte[] writeInt32(int value) {
        return new byte[]{
                (byte) ((value >>> 24) & 0xFF),
                (byte) ((value >>> 16) & 0xFF),
                (byte) ((value >>> 8) & 0xFF),
                (byte) (value & 0xFF)
        };
    }

    public static int readInt32(byte[] bytes) {
        return ((bytes[0] & 0xff) << 24) +
                ((bytes[1] & 0xff) << 16) +
                ((bytes[2] & 0xff) << 8) +
                (bytes[3] & 0xff);
    }

    public static byte[] writeUInt32(int value) {
        return new byte[]{
                (byte) (value & 0xFF),
                (byte) ((value >>> 8) & 0xFF),
                (byte) ((value >>> 16) & 0xFF),
                (byte) ((value >>> 24) & 0xFF)
        };
    }


    public static int readUInt32(byte[] bytes) {
        return ((bytes[3] & 0xff) << 24) +
                ((bytes[2] & 0xff) << 16) +
                ((bytes[1] & 0xff) << 8) +
                (bytes[0] & 0xff);
    }


    public static long readInt64(byte[] bytes) {
        return (((long) bytes[0] << 56) +
                ((long) (bytes[1] & 0xFF) << 48) +
                ((long) (bytes[2] & 0xFF) << 40) +
                ((long) (bytes[3] & 0xFF) << 32) +
                ((long) (bytes[4] & 0xFF) << 24) +
                ((bytes[5] & 0xFF) << 16) +
                ((bytes[6] & 0xFF) << 8) +
                ((bytes[7] & 0xFF)));
    }

    public static byte[] writeInt64(long l) {
        return new byte[]{
                (byte) (l >>> 56),
                (byte) (l >>> 48),
                (byte) (l >>> 40),
                (byte) (l >>> 32),
                (byte) (l >>> 24),
                (byte) (l >>> 16),
                (byte) (l >>> 8),
                (byte) (l)
        };
    }

    public static long readUInt64(byte[] bytes) {
        return (((long) bytes[7] << 56) +
                ((long) (bytes[6] & 0xFF) << 48) +
                ((long) (bytes[5] & 0xFF) << 40) +
                ((long) (bytes[4] & 0xFF) << 32) +
                ((long) (bytes[3] & 0xFF) << 24) +
                ((bytes[2] & 0xFF) << 16) +
                ((bytes[1] & 0xFF) << 8) +
                ((bytes[0] & 0xFF)));
    }

    public static byte[] writeUInt64(long l) {
        return new byte[]{
                (byte) (l),
                (byte) (l >>> 8),
                (byte) (l >>> 16),
                (byte) (l >>> 24),
                (byte) (l >>> 32),
                (byte) (l >>> 40),
                (byte) (l >>> 48),
                (byte) (l >>> 56),
        };
    }


    public static float readFloat32(byte[] bytes) {
        return readFloat32(bytes, -1);
    }

    public static float readFloat32(byte[] bytes, int accuracy) {
        float val = Float.intBitsToFloat(readInt32(bytes));
        if (accuracy > -1) {
            return (float) BSMath.round(val, accuracy);
        } else {
            return val;
        }
    }

    public static byte[] writeFloat32(float f) {
        return writeInt32(Float.floatToIntBits(f));
    }

    public static float readUFloat32(byte[] bytes) {
        return readUFloat32(bytes, -1);
    }

    public static float readUFloat32(byte[] bytes, int accuracy) {
        float val = Float.intBitsToFloat(readUInt32(bytes));
        if (accuracy > -1) {
            return (float) BSMath.round(val, accuracy);
        } else {
            return val;
        }
    }

    public static byte[] writeUFloat32(float f) {
        return writeUInt32(Float.floatToIntBits(f));
    }

    public static double readFloat64(byte[] bytes) {
        return Double.longBitsToDouble(readInt64(bytes));
    }

    public static byte[] writeFloat64(double d) {
        return writeInt64(Double.doubleToLongBits(d));
    }

    public static double readUFloat64(byte[] bytes) {
        return Double.longBitsToDouble(readUInt64(bytes));
    }

    public static byte[] writeUFloat64(double d) {
        return writeUInt64(Double.doubleToLongBits(d));
    }

}
