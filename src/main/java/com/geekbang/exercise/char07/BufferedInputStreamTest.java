package com.geekbang.exercise.char07;

import java.io.*;

/**
 *  完成操作二进制文件，当然也可以操作文本文件（字符）
 * **/
public class BufferedInputStreamTest {
    public static void main(String[] args) {
        String srcFilePath = "/Users/qiany/Desktop/ccc.png";
        String destFilePath = "/Users/qiany/Desktop/ccc_copy.png";

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            bis = new BufferedInputStream(new FileInputStream(srcFilePath));
            bos = new BufferedOutputStream(new FileOutputStream(destFilePath));

            int readLen = 0;
            byte[] buf = new byte[1024];

            while ((readLen = bis.read(buf)) != -1) {
                bos.write(buf, 0, readLen);
            }
            System.out.println("拷贝成功");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
