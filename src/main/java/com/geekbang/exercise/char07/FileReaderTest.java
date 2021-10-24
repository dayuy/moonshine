package com.geekbang.exercise.char07;

import java.io.FileReader;
import java.io.IOException;

public class FileReaderTest {
    public static void main(String[] args) {

        String filePath = "/Users/qiany/Desktop/d.txt";
        FileReader fileReader = null;
        // int data = 0;
        int readLen = 0;
        char[] buf = new char[8];

        try {
            fileReader = new FileReader(filePath);
            /**
             * fileReader.read() 一个一个字符的读取；读取到的是一个字符
             * while ((data = fileReader.read()) != -1) {
             *   System.out.println((char) data);
             * }
             * **/
            // 循环读取，fileReader.read(buf) 返回实际读取到的字符数，如果返回-1，表示读取完毕
            while ((readLen = fileReader.read(buf)) != -1) {
                System.out.println(new String(buf, 0, readLen));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
