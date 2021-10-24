package com.geekbang.exercise.char07;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileTest {
    public static void main(String[] args) {

    }


    @Test // 方式一：file = new File(filePath); file.createNewFile();
    public void create01() {
        String filePath = "/Users/qiany/Desktop/news1.txt";
        File file = new File(filePath);
        try {
            file.createNewFile();
            System.out.println("创建成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test // 方法2：new File(File parent, String child)  根据父目录文件+子路径构建
    public void create02() {
        File parentFile = new File("/Users/qiany/Desktop/"); // 父目录文件
        String fileName = "news2.txt";
        // 这里的file对象，在java程序中，只是一个对象
        // 只有执行了 createNewFile 方法，才会真正的，在磁盘创建该文件
        File file = new File(parentFile, fileName);
        try {
            file.createNewFile();
            System.out.println("创建成功～");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test // 方式3：new File(String parent, String child) 根据父目录+子路径构建
    public void create03() {
        String parentPath = "/Users/qiany/Desktop/";
        String fileName = "news3.txt";
        File file = new File(parentPath, fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void create04() {
        File file = new File("/Users/qiany/Desktop/news2.txt");
        System.out.println(file.getName());
        System.out.println(file.getAbsoluteFile());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getParent());
        System.out.println(file.length());
        System.out.println(file.exists());
        System.out.println(file.isFile());
        System.out.println(file.isDirectory());

        if (file.exists()) {
            if (file.delete()) {
                System.out.println("删除成功！");
            } else {
                System.out.println("删除失败！");
            }
        } else {
            System.out.println("文件不存在！");
        }

        String directoryPath = "/Users/qiany/Desktop/haha/baba";
        File file1 = new File(directoryPath);
        if (file1.exists()) {
            System.out.println(directoryPath + "存在。。。");
        } else {
            if (file1.mkdirs()) { // mkdirs() 创建多级目录、mkdir()创建一级目录
                System.out.println(directoryPath + "该目录创建成功");
            } else {
                System.out.println("创建失败");
            }
        }
    }

    @Test
    public void readFile01() {
        String filepath = "/Users/qiany/Desktop/news1.txt";
        int readData = 0;
        int readLen = 0;
        FileInputStream fileInputStream = null;
        byte[] buf = new byte[8]; // 4、字节数组，可以一次读取8个字节
        try {
            // 1、创建 FileInputStream 对象，用于读取文件
            fileInputStream = new FileInputStream(filepath);
            // 2、从该输入流读取一个字节的数据，如果没有输入可用，此方法将被阻止；如果返回-1，表示读取完毕
            //    4.1、fileInputStream.read() 这里只能一次读取一个 返回的是读取到的内容
            // while ((readData = fileInputStream.read()) != -1) {
            //     System.out.println((char) readData);
            // 3、fileInputStream.read(buf) 如果读取正常，返回的是实际读取的字节数
            while ((readLen = fileInputStream.read(buf)) != -1) { // 5、从该输入流读取最多8个字节的数据到buf数组中
                System.out.println(new String(buf, 0, readLen));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close(); // 关闭文件流，释放资源
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    // FileOutputStream
    @Test
    public void writeFile01() {
        String filePath = "/Users/qiany/Desktop/a.txt";
        FileOutputStream fileOutputStream = null;

        try {
            // 得到fileOutputStream对象
            fileOutputStream = new FileOutputStream(filePath, true);
            // 写入一个字节
            // fileOutputStream.write('H');
            // 写入字符串  ** 字符串 -> 字节数组 str.getBytes();
            //              字符串 -> char[] str.toCharArray();
            String str = "hello,world!";
            fileOutputStream.write(str.getBytes());
            // fileOutputStream.write(str.getBytes(), 0, str.length());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



















