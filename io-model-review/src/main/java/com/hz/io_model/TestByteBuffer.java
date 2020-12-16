package com.hz.io_model;

import java.nio.ByteBuffer;

/**
 * @author zehua
 * @date 2020/12/11 9:11
 */
public class TestByteBuffer {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(50);
        byteBuffer.mark();
        byteBuffer.reset();
        byteBuffer.put("hello zehua".getBytes());
        System.out.println(byteBuffer.toString());
        byteBuffer.put("world server".getBytes());
        System.out.println(byteBuffer.toString());


        byteBuffer.flip();
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        byte[] bb = new byte[byteBuffer.limit()];
        byteBuffer.get(bb);
        System.out.println(new String(bb, 0,bb.length));

        byte[] bytes = new byte[3];
        byteBuffer.flip();
        byteBuffer.get(bytes);
        System.out.println(new String(bytes, 0, 3));
        System.out.println(byteBuffer);
        byteBuffer.compact();
        System.out.println(byteBuffer);

        byteBuffer.flip();
        System.out.println(byteBuffer);
        byteBuffer.put("abcc".getBytes());

        byte[] bbb = new byte[byteBuffer.limit()-byteBuffer.position()];
        byteBuffer.get(bbb);
        System.out.println(new String(bbb, 0,bbb.length));
        //final byte b = byteBuffer.get();
        //System.out.println(b);
        System.out.println(byteBuffer.toString());
    }

}
