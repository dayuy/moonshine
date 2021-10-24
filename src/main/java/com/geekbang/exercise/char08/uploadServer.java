package com.geekbang.exercise.char08;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class uploadServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("服务端 8888 监听");

        Socket socket = serverSocket.accept();

        // 1、读取客户端发送的数据
        BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
        byte[] bytes = StreamUnits.streamToByteArray(bis);
        // 2、将得到的 bytes数组，写入到指定的路径，就得到一个文件
        String destFilepath = "/Users/qiany/Desktop/love.png";
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFilepath));
        bos.write(bytes);
        bos.close();

        // 向客户端回复 "收到图片"
        // 通过 socket 获取到输出流（并转成字符流）
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        writer.write("收到图片");
        writer.flush(); // 把内容刷新到数据通道
        socket.shutdownOutput();

        bis.close();
        socket.close();
        serverSocket.close();
    }
}
