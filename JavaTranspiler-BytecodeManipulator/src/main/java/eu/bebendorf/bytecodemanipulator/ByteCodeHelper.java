package eu.bebendorf.bytecodemanipulator;

import java.nio.ByteBuffer;

public class ByteCodeHelper {

    public static int readUnsignedShort(ByteBuffer byteBuffer) {
        return toUnsignedShort(byteBuffer.getShort());
    }

    public static int toUnsignedShort(short s) {
        ByteBuffer bb = ByteBuffer.allocate(4);
        bb.putShort((short) 0);
        bb.putShort(s);
        bb.position(0);
        return bb.getInt();
    }

    public static long readUnsignedInt(ByteBuffer byteBuffer) {
        return toUnsignedInt(byteBuffer.getInt());
    }

    public static long toUnsignedInt(int i) {
        ByteBuffer bb = ByteBuffer.allocate(8);
        bb.putInt(0);
        bb.putInt(i);
        bb.position(0);
        return bb.getLong();
    }

    public static int readUnsignedByte(ByteBuffer byteBuffer) {
        return toUnsignedByte(byteBuffer.get());
    }

    public static int toUnsignedByte(byte b) {
         return b & 0xff;
    }

    public static short toSignedShort(byte[] bytes) {
        ByteBuffer bb = ByteBuffer.wrap(bytes);
        return bb.getShort();
    }

    public static byte[] toBytes(int s){
        ByteBuffer bb = ByteBuffer.allocate(4);
        bb.putInt(s);
        bb.position(0);
        return bb.array();
    }

    public static byte[] toBytes(short s){
        ByteBuffer bb = ByteBuffer.allocate(2);
        bb.putShort(s);
        bb.position(0);
        return bb.array();
    }

    public static byte[] toBytes(float s){
        ByteBuffer bb = ByteBuffer.allocate(4);
        bb.putFloat(s);
        bb.position(0);
        return bb.array();
    }

    public static byte[] toBytes(double s){
        ByteBuffer bb = ByteBuffer.allocate(8);
        bb.putDouble(s);
        bb.position(0);
        return bb.array();
    }

    public static byte[] toBytes(long s){
        ByteBuffer bb = ByteBuffer.allocate(8);
        bb.putLong(s);
        bb.position(0);
        return bb.array();
    }

    public static byte toByte(int value){
        ByteBuffer bb = ByteBuffer.allocate(4);
        bb.putInt(value);
        bb.position(3);
        return bb.get();
    }

    public static byte[] toSignedShortBytes(int s){
        ByteBuffer bb = ByteBuffer.allocate(4);
        bb.putInt(s);
        bb.position(2);
        byte[] bytes = new byte[2];
        bb.get(bytes);
        return bytes;
    }

}
