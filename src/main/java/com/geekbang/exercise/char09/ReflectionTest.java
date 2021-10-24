package com.geekbang.exercise.char09;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

// 反射解决的问题
public class ReflectionTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        // 传统方法 new 对象，然后调用方法
        // Cat cat = new Cat();
        // cat.hi(); -> cat.cry(); 需要修改原码

        // 根据配置文件 re.properties 指定信息，创建Cat对象并调用方法hi
        // 1、使用Properties类，可以读写配置文件
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/re.properties"));
        String classfullpath = properties.get("classfullpath").toString();
        String methodName = properties.get("method").toString();
        System.out.println("classfullpath=" + classfullpath);
        System.out.println("method=" + methodName);
        // 创建对应的类，classfullpath只是个字符串
        // new classfullpath();

        // 3、使用反射机制解决
        //    1)、加载类，返回Class类型的对象cls
        Class cls = Class.forName(classfullpath);
        //    2）、通过cls得到加载的类 com.hspedu.Cat 的对象实例
        Object o = cls.newInstance();
        System.out.println("o的运行类型=" + o.getClass()); // 运行类型
        //    3）、通过cls.getMethod() 得到加载类的某些方法对象
        //        即：在反射中，可以把方法视为对象（万物皆对象）
        Method method1 = cls.getMethod(methodName);
        System.out.println("======================");
        //    4）、通过 method1 调用方法：即通过方法对象来实现调用方法
        method1.invoke(o);

        // java.lang.reflect.Field 表示类的成员变量
        // Field nameField = cls.getField("name"); getField() 不能得到私有属性
        Field ageField = cls.getField("age");
        System.out.println(ageField.get(o)); // 反射：成员变量.get(对象)

        Constructor constructor = cls.getConstructor(); // 返回无参构造器
        System.out.println(constructor); // Cat()

        Constructor constructor1 = cls.getConstructor(String.class); // 传入的是String类的class对象
        System.out.println(constructor1); // Cat(String name)
    }
}























