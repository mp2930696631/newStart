package com.hz.io_model.nioChat;

import java.io.BufferedInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author zehua
 * @date 2020/12/11 11:44
 */
public class Utils {

    public static void readThenWriteData(SocketChannel socket, ByteBuffer byteBuffer) {
        try {
            byteBuffer.flip();
            byte[] bytes = new byte[byteBuffer.remaining()];
            byteBuffer.get(bytes);
            System.out.println(new String(bytes, 0, bytes.length));

            BufferedInputStream inputStream = new BufferedInputStream(System.in);
            byte[] buffer = new byte[1024];
            final int size = inputStream.read(buffer);
            byteBuffer.clear();
            byteBuffer.put(buffer, 0, size);
            byteBuffer.flip();
            socket.write(byteBuffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
