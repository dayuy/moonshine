package com.geekbang.exercise.char07;

import java.io.*;

public class FileCopyTest {
    public static void main(String[] args) {
        // 文件copy
        // 1、创建文件的输入流，将文件读入到程序
        // 2、创建文件的输出流，将读取的文件数据，写入到指定的文件。
//        String srcFilePath = "/Users/qiany/Downloads/96A7D681EDE95EDFACC5F27FCA0C0987.png";
//        String desFilePath = "/Users/qiany/Desktop/ccc.png";
        String srcFilePath = "/Users/qiany/Desktop/a.txt";
        String desFilePath = "/Users/qiany/Desktop/d.txt";
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;

        try {
            fileInputStream = new FileInputStream(srcFilePath);
            fileOutputStream = new FileOutputStream(desFilePath);
            // 定义一个字节数组，提高读取效果
            byte[] buf = new byte[102];
            int readLen = 0;
            while ((readLen = fileInputStream.read(buf)) != -1) {
                // 读取到后，就写入到文件。边读边写入到文件
                // 注意：这个方法，会写入到buf的所有内容。1058但仅剩余34byte时。
                //      这个buf仅前34位是新读取到的，后面依然是上次读取的没有变。
                //      fileOutputStream.write(buf);
                fileOutputStream.write(buf, 0, readLen);
            }
            System.out.println("copy 成功");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}




























