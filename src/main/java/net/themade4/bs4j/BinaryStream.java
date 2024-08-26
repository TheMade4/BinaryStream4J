package net.themade4.bs4j;


import net.themade4.bs4j.utils.BinaryPacker;

import java.util.Arrays;

public class BinaryStream {
    private static final int MAX_ARRAY_SIZE = (Integer.MAX_VALUE - 8);
    private byte[] buffer;
    private int offset;
    private int size;

    public BinaryStream() {
        this.buffer = new byte[32];
        this.offset = 0;
        this.size = 0;
    }

    public byte[] getBuffer() {
        return buffer;
    }

    public BinaryStream setBuffer(byte[] buffer) {
        this.buffer = buffer;
        return this;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) {
            throw new OutOfMemoryError();
        }
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }

    private void expandBuffer(int size) {
        if (size - buffer.length <= 0) {
            return;
        }

        int oldCapacity = buffer.length;
        int newCapacity = oldCapacity << 1;

        if (newCapacity - size < 0) {
            newCapacity = size;
        }

        if (newCapacity - MAX_ARRAY_SIZE > 0) {
            newCapacity = hugeCapacity(size);
        }
        this.buffer = Arrays.copyOf(buffer, newCapacity);
    }

    public BinaryStream write(byte[] bytes) {
        if (bytes == null) {
            return this;
        }

        this.expandBuffer(this.size + bytes.length);
        System.arraycopy(bytes, 0, this.buffer, this.size, bytes.length);
        this.size += bytes.length;

        return this;
    }

    public byte[] read() {
        return this.read(this.size - this.offset);
    }

    public byte[] read(int length) {
        if (length < 0) {
            this.offset = this.size - 1;
            return new byte[0];
        }

        length = Math.min(length, this.size - this.offset);
        this.offset += length;
        return Arrays.copyOfRange(this.buffer, this.offset - length, this.offset);
    }

    public BinaryStream writeByte(byte value) {
        this.write(new byte[]{value});
        return this;
    }

    public BinaryStream writeBool(boolean value) {
        return this.writeByte(BinaryPacker.writeBool(value));
    }

    public BinaryStream writeInt8(char value) {
        return this.writeByte(BinaryPacker.writeInt8(value));
    }

    public BinaryStream writeInt16(short value) {
        return this.write(BinaryPacker.writeInt16(value));
    }

    public BinaryStream writeInt24(int value) {
        return this.write(BinaryPacker.writeInt24(value));
    }

    public BinaryStream writeInt32(int value) {
        return this.write(BinaryPacker.writeInt32(value));
    }

    public BinaryStream writeInt64(long value) {
        return this.write(BinaryPacker.writeInt64(value));
    }

    public BinaryStream writeUInt16(short value) {
        return this.write(BinaryPacker.writeUInt16(value));
    }

    public BinaryStream writeUInt24(int value) {
        return this.write(BinaryPacker.writeUInt24(value));
    }

    public BinaryStream writeUInt32(int value) {
        return this.write(BinaryPacker.writeUInt32(value));
    }

    public BinaryStream writeUInt64(long value) {
        return this.write(BinaryPacker.writeUInt64(value));
    }

    public BinaryStream writeFloat32(long value) {
        return this.write(BinaryPacker.writeFloat32(value));
    }

    public BinaryStream writeFloat64(long value) {
        return this.write(BinaryPacker.writeFloat64(value));
    }

    public BinaryStream writeUFloat32(long value) {
        return this.write(BinaryPacker.writeUFloat32(value));
    }

    public BinaryStream writeUFloat64(long value) {
        return this.write(BinaryPacker.writeUFloat64(value));
    }

    public boolean readBool() {
        return BinaryPacker.readBool(this.read(1)[0]);
    }

    public char readInt8() {
        return BinaryPacker.readInt8(this.read(1)[0]);
    }

    public short readInt16() {
        return BinaryPacker.readInt16(this.read(2));
    }

    public int readInt24() {
        return BinaryPacker.readInt24(this.read(3));
    }

    public int readInt32() {
        return BinaryPacker.readInt32(this.read(4));
    }

    public long readInt64() {
        return BinaryPacker.readInt64(this.read(8));
    }

    public short readUInt16() {
        return BinaryPacker.readUInt16(this.read(2));
    }

    public int readUInt24() {
        return BinaryPacker.readUInt24(this.read(3));
    }

    public int readUInt32() {
        return BinaryPacker.readUInt32(this.read(4));
    }

    public long readUInt64() {
        return BinaryPacker.readUInt64(this.read(8));
    }

    public float readFloat32() {
        return BinaryPacker.readFloat32(this.read(4));
    }

    public double readFloat64() {
        return BinaryPacker.readFloat64(this.read(8));
    }

    public float readUFloat32() {
        return BinaryPacker.readUFloat32(this.read(4));
    }

    public double readUFloat64() {
        return BinaryPacker.readUFloat64(this.read(8));
    }

}
