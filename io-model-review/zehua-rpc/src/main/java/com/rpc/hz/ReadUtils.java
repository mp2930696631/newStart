package com.rpc.hz;

import com.rpc.hz.procotol.RpcBody;
import com.rpc.hz.procotol.RpcHeader;
import io.netty.buffer.ByteBuf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author zehua
 * @date 2020/12/12 15:40
 */
public class ReadUtils {

    public static RpcHeader readHeader(ByteBuf byteBuf, int size) {
        try {
            byte[] headerBuffer = new byte[size];
            byteBuf.readBytes(headerBuffer);
            ByteArrayInputStream bais = new ByteArrayInputStream(headerBuffer);
            ObjectInputStream ois = new ObjectInputStream(bais);
            final RpcHeader header = (RpcHeader) ois.readObject();

            return header;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static RpcBody readBody(ByteBuf byteBuf, int size) {
        try {
            byte[] bodyBuffer = new byte[size];
            byteBuf.readBytes(bodyBuffer);
            ByteArrayInputStream bais = new ByteArrayInputStream(bodyBuffer);
            ObjectInputStream ois = new ObjectInputStream(bais);
            final RpcBody body = (RpcBody) ois.readObject();

            return body;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static byte[] getObjBytes(Object obj) {
        try (
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
        ) {
            oos.writeObject(obj);
            final byte[] bytes = baos.toByteArray();

            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

}
