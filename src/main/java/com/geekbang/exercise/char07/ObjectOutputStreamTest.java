package com.geekbang.exercise.char07;

import java.io.*;

public class ObjectOutputStreamTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String filePath = "/Users/qiany/Desktop/data.dat";
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));

        oos.write(100); // int -> Integer（实现了 Serializable）
        oos.writeBoolean(true);
        oos.writeDouble(9.5);
        oos.writeUTF("哈哈");
        oos.writeObject(new Dog("大黄", 2)); // 实现了 Serializable
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
        // 反序列化，需要和保存时的顺序一致
        System.out.println(ois.read());
        System.out.println(ois.readBoolean());
        System.out.println(ois.readDouble());
        System.out.println(ois.readUTF());
        System.out.println((Dog) ois.readObject()); // 此Dog必须与存入时的一致
        ois.close();
    }
}
class Dog implements Serializable {
    private String name;
    private int age;
    // 2、序列化对象时，默认将里面的所有属性都进行序列化，除了static和transient修饰的成员
    // 3、序列化对象时，要求里面的属性的类型也需要实现序列化接口
    // 1、serialVersionUID 序列化的版本号，提高兼容性，新增了方法不会影响其变成另一个序列化
    private static final long serialVersionUID = 1L;
    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }
    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
