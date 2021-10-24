package com.geekbang.exercise.char07;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// BufferedReader 不能操作二进制文件
public class BufferReaderTest {
    public static void main(String[] args) throws IOException {
        String filePath = "/Users/qiany/Desktop/a.txt";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        String line; // 按行读取
        /**
         * 1、bufferedReader.readline() 是按行读取文件
         * 2、当返回null时，表示文件读取完毕
         * **/
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
        // 关闭流，只需要关闭BufferedReader，因为底层会自动去关闭节点流 FileReader
        bufferedReader.close();
    }
}
