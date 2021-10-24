package com.geekbang.exercise.char08;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class SocketTCPClientTest {
    public static void main(String[] args) throws IOException {
        // 1、连接服务端（ip, 端口），如果连接成功，返回Socket对象
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        System.out.println("客户端 socket返回= " + socket.getClass());
        // 2、连接上后，生成Socket，得到和socket相关联的输出流对象
        OutputStream outputStream = socket.getOutputStream();

        /**
         * // 3、通过输出流，写入数据到 数据通道
         * outputStream.write("hello, server".getBytes());
         * // 6、设置写入结束标记
         * socket.shutdownOutput();
         * **/

        // 使用字符流，写入数据到数据通道
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("Hello, server 字符流");
        bufferedWriter.newLine(); // 插入一个换行符，表示写入的内容结束，注意：这里得要求对方使用readLine()
        bufferedWriter.flush();

        // 5、读取
        InputStream inputStream = socket.getInputStream();
        int readLen = 0;
        byte[] buf = new byte[8];
        while ((readLen = inputStream.read(buf)) != -1) {
            System.out.println(new String(buf, 0, readLen));
        }
        // 4、关闭流对象和socket
        inputStream.close();
        outputStream.close();
        socket.close();
        System.out.println("客户端退出.....");
    }
}
