package com.geekbang.exercise.char08;

import java.io.IOException;
import java.net.*;

public class UDPsenderB {
    public static void main(String[] args) throws IOException {
        // 1、创建 DatagramSocket 对象，准备在 9998端口 接收数据
        DatagramSocket socket = new DatagramSocket(9998);

        // 2、将需要发送的数据，封装到 DatagramPacket 对象
        byte[] data = "hello 明天想吃火锅么？".getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName("192.168.1.4"), 9999);
        // 3、发送
        socket.send(packet);
        socket.close();
    }
}
