package com.zehua.review.net.testUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * @Auther zehua
 * @Date 2020/10/30 13:10
 */
public class Client {

    public static void main(String[] args) {
        try (
                DatagramSocket socket = new DatagramSocket(10000);

        ) {
            byte[] buffer = "hello 你好".getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getLocalHost(), 10086);
            socket.send(packet);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
