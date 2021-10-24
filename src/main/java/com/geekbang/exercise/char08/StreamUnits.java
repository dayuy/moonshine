package com.geekbang.exercise.char08;

import java.io.*;

public class StreamUnits {

    // 将输入流转换成byte[]，即可以把文件的内容读入到byte[] (从磁盘到程序里,输入流)
    public static byte[] streamToByteArray(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int len;
        while ((len = is.read(b))!=-1) {
            bos.write(b, 0, len);
        }
        byte[] array = bos.toByteArray();
        bos.close();
        return array;
    }

    public static String streamToString(InputStream is) throws IOException {
        InputStreamReader bis = new InputStreamReader(is);
        CharArrayWriter charArrayWriter = new CharArrayWriter();
        char[] c = new char[1024];
        int len;
        while ((len = bis.read(c)) != -1) {
            charArrayWriter.write(c, 0, len);
        }
        String r = charArrayWriter.toString();
        bis.close();
        charArrayWriter.close();
        return r;
    }

    public static String streamToString_(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line+"\r\n");
        }
        return builder.toString();
    }
}














