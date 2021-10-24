package com.geekbang.exercise.char08;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

// UDP 接收数据
public class UDPreceiverA {
    public static void main(String[] args) throws IOException {
        // 1、创建一个 DatagramSocket 对象，准备在 9999 接收数据
        DatagramSocket socket = new DatagramSocket(9999);
        // 2、构建一个 DatagramPacket 对象，准备接收数据
        byte[] buf = new byte[64 * 1024]; // UDP协议中，一个数据包最大64K
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        // 3、调用 接收方法，将通过网络传输的 DatagramPacket 对象填充到 packet 对象
        //    当有数据包发送到本机9999端口时，就会接收到数据。没有则会阻塞等待。。。
        System.out.println("接收端A 等待接收数据...");
        socket.receive(packet);

        // 4、可以把packet进行拆包，取出数据，并显示。
        int length = packet.getLength(); // 实际接收到的数据字节长度
        byte[] data = packet.getData(); // 接收到的数据
        String s = new String(data, 0, length);
        System.out.println(s);

        socket.close();
    }
}
























