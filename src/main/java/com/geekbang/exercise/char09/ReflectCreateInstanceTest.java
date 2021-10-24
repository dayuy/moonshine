package com.geekbang.exercise.char09;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

// 通过反射机制，创建实例的四种方法
public class ReflectCreateInstanceTest {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        // 1、选获取User类的Class对象
        Class<?> aClass = Class.forName("com.geekbang.exercise.char09.User");
        // 2、通过public的无参构造器创建实例
        Object o = aClass.newInstance();
        // 3、通过public的有参构造器创建实例：先得到构造器，再传入实参
        Constructor<?> constructor = aClass.getConstructor(Integer.class);
        Object o1 = constructor.newInstance(20);
        // 4、通过非public的有惨构造器创建实例：先得到private构造器对象，创建实例
        Constructor<?> constructor1 = aClass.getDeclaredConstructor(int.class, String.class);
        constructor1.setAccessible(true); // 爆破，使用反射可以访问private构造器/方法/属性
        Object user2 = constructor1.newInstance(100, "bbbb");

        // 5、使用反射操作private name属性
        Field name = aClass.getDeclaredField("name");
        name.setAccessible(true); // 爆破
        name.set(o, "aaaa");
        name.set(null, "aaaa"); // 因为name是static属性，所以也可以是null
        System.out.println(name.get(o));
        System.out.println(name.get(null));
    }
}

class User {
    private int age = 10;
    private String name = "aaa";
    public User() {

    }

    public User(int age) {
        this.age = age;
    }

    private User(int age, String name) {
        this.age = age;
        this.name = name;
    }
}
