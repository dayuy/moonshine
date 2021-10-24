package com.geekbang.exercise.char08;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

// 文件下载
class homework03_server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        byte[] b = new byte[1024];
        int len = 0;
        String downloadFileName = "";
        while ((len = inputStream.read(b)) != -1) {
            downloadFileName += new String(b, 0, len);
        }
        System.out.println("客户端希望下载文件：" + downloadFileName);

        String resFileName = "";
        if ("高山流水".equals(resFileName)) {
            resFileName = "/";
        }
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(resFileName));
        byte[] bytes = StreamUnits.streamToByteArray(bis);

        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
        bos.write(bytes);
        socket.shutdownOutput();

        bis.close();
        inputStream.close();
        socket.close();
        serverSocket.close();
        System.out.println("服务端退出。。。。");
    }
}

class homework03_client {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入");
        String downloadFileName = scanner.next();

        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(downloadFileName.getBytes());
        socket.shutdownOutput();

        BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
        byte[] bytes = StreamUnits.streamToByteArray(bis);

        String filepath = "/haha";
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filepath));
        bos.write(bytes);

        bos.close();
        bis.close();
        socket.close();
        outputStream.close();
    }
}






























