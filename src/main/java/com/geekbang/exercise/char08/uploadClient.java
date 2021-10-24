package com.geekbang.exercise.char08;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

// 通过socket完成上传文件
public class uploadClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(), 8888);
        // 1、创建读取磁盘文件的输入流
        String filepath = "/Users/qiany/Desktop/ccc.png";
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filepath));
        // 2、文件读取到字节数组中 bytes
        byte[] bytes = StreamUnits.streamToByteArray(bis);
        // 3、通过socket获取到输出流，给服务端，并结束
        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
        bos.write(bytes);
        bis.close();
        socket.shutdownOutput(); //设置写入数据的结束标记

        // 接收从服务端回复的消息
        InputStream inputStream = socket.getInputStream();
        // 使用StreamUtils的方法，直接从 inputStream 读取到的内容转成字符串
        String s = StreamUnits.streamToString(inputStream);
        System.out.println(s);

        inputStream.close();
        bos.close();
        socket.close();
    }
}
