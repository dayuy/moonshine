package com.geekbang.exercise.char08;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketTCPServerTest {
    public static void main(String[] args) throws IOException {
        // 1、监听本机端口 9999，等待连接 （要求端口未被占用）
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("服务端，在监听9999端口，等待....." + serverSocket);
        // 2、当没有客户端连接 9999 端口时，会堵塞在这里，等待连接，不会向下执行
        // 3、如果有客户端连接，则会返回 Socket 对象，程序继续
        Socket socket = serverSocket.accept(); // 可以多次调取accept()，返回多个socket【多个客户端连接服务器】
        System.out.println("服务端 socket =" + socket.getClass());
        // 4、通过socket.getInputStream() 读取客户端写入到数据通道的数据，显示
        InputStream inputStream = socket.getInputStream();
        // 使用字符流读取
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String s = bufferedReader.readLine();
        System.out.println(s);

        /***
         *  // 5、IO 读取
         *  byte[] buf = new byte[1024];
         *  int readLen = 0;
         *  while ((readLen = inputStream.read(buf)) != -1) {
         *     System.out.println(new String(buf, 0, readLen));
         *  }
         * **/
        // 7、回复
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("Hello, client".getBytes());
        // 8、设置 socket结束
        socket.shutdownOutput();
        // 6、关闭
        outputStream.close();
        inputStream.close();
        socket.close();
        serverSocket.close();
    }
}

