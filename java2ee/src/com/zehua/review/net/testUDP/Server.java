package com.zehua.review.net.testUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @Auther zehua
 * @Date 2020/10/30 13:10
 */
public class Server {

    public static void main(String[] args) {
        try (
                DatagramSocket socket = new DatagramSocket(10086);
        ) {
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);

            System.out.println(new String(packet.getData(), packet.getOffset(), packet.getLength()));
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
