package com.geekbang.exercise.char07;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriteTest {
    public static void main(String[] args) {
        String filePath = "/Users/qiany/Desktop/news1.txt";
        FileWriter fileWriter = null;
        char[] chars = new char[]{'a', 'b', 'c'};

        try {
            fileWriter = new FileWriter(filePath);
            fileWriter.write('H');
            fileWriter.write(chars);
            fileWriter.write(" 哈哈哈劳力～".toCharArray(), 0, 5);
            fileWriter.write(" 哈哈哈劳力～");
            fileWriter.write(" 哈哈哈劳力～", 3,4);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileWriter != null) {
                    // 只有执行这个close();或 flush();才会真正写入，执行fileOutStream.writeBytes()
                    fileWriter.close();
                    // fileWriter.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
