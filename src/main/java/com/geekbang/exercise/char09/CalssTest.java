package com.geekbang.exercise.char09;

import java.io.Serializable;
import java.lang.reflect.Field;

// 对 Class 类的特点进行梳理
public class CalssTest {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        // 1、Class也是类，也继承自Object类
        // 2、Class类对象不是new出来的，而是系统创建的
        // 1）、传统new对象
        Cat cat = new Cat();
        /**
         * public loadClass(String name) throws ClassNotFoundException {
         *      return loadClass(name, false);   // name: "com.geekbang.exercise.char09.Cat"
         * }
         * **/
        // 2)、反射方式，一样是通过ClassLoader.loadclass加载Cat的Class对象
        Class aClass = Class.forName("com.geekbang.exercise.char09.Cat");
        // 3、对于某个类的Class类对象，在内存中只有一份，因为类只加载一次
        Class<?> aClass1 = Class.forName("com.geekbang.exercise.char09.Cat");
        System.out.println(aClass1.hashCode());
        System.out.println(aClass.hashCode());
        // 4、Class对象是存放在堆中的
        // 5、类的字节码二进制数据，是放在方法区的，有的地方称为类的元数据（方法代码、变量名、方法名、访问权限）

        t1();
        t3();
    }

    public static void t1() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        System.out.println("================");
        String classAllPath = "com.geekbang.exercise.char09.Cat";
        // 1、获取到Cat类 对应的Class对象
        Class<?> cls = Class.forName(classAllPath);
        System.out.println(cls); // 显示cls对象，是哪个类的Class对象
        System.out.println(cls.getClass()); // cls对象的运行类型 java.lang.Class
        System.out.println(cls.getPackage().getName()); // 包名
        System.out.println(cls.getName()); // 全类名
        Cat cat = (Cat) cls.newInstance(); // 通过cls对象创建一个相应的对象实例
        System.out.println(cat); // cat.toString()
        Field age = cls.getField("age"); // 通过反射获取属性
        System.out.println(age.get(cat)); //
        age.set(cat, 40); // 设置
        System.out.println(age.get(cat));
        Field[] fields = cls.getFields(); // 获取所有的属性
        for (Field f : fields) {
            System.out.println(f.getName());
        }
    }

    // 得到class类对象的方式
    public static void t2() throws ClassNotFoundException {
        String classAllPath = "com.geekbang.exercise.char09.Cat";
        Cat cat = new Cat();
        // 1、 编译阶段：Class类的静态方法获取 Class.forName() （条件：已知类的全类名）场景：用于配置文件
        Class<?> aClass = Class.forName(classAllPath);
        // 2、加载阶段：通过该类实例的class属性获取，Cat.class （已知具体的类，最为安全可靠）用于参数传递
        Class<Cat> catClass = Cat.class;
        // 3、运行阶段：实例.getClass() (已有运行实例)
        Class<? extends Cat> aClass1 = cat.getClass();
        // 4、通过类加载器获取Class对象
        //  1）得到类加载器
        ClassLoader classLoader = cat.getClass().getClassLoader();
        //  2) 通过类加载器得到Class对象
        Class<?> cls4 = classLoader.loadClass(classAllPath);
        // 5、基本数据类型 int,char,boolean,float,double,byte,long,short 通过.class得到Class类对象
        Class<Integer> integerClass = int.class;
        Class<Character> characterClass = char.class;
        Class<Boolean> booleanClass = boolean.class;
        Class<Float> floatClass = float.class;
        // 6、基本数据类型对应的包装类，可以通过 .TYPE 得到Class类对象
        Class<Integer> type = Integer.TYPE;
        Class<Character> type1 = Character.TYPE;
    }

    // 哪些类型有Class对象
    public static void t3() {
        Class<String> stringClass = String.class; // 外部类  ：class java.lang.String
        Class<Serializable> serializableClass = Serializable.class; // 接口  ：interface java.io.Serializable
        Class<Integer[]> aClass = Integer[].class; // 数组  ：class [Ljava.lang.Integer;
        Class<float[][]> aClass1 = float[][].class; // 二维数组  ：class [[F
        Class<Deprecated> deprecatedClass = Deprecated.class; // 注解  ：interface java.lang.Deprecated
        Class<Thread.State> stateClass = Thread.State.class; // 枚举  ：class java.lang.Thread$State
        Class<Long> longClass = long.class; // 基本数据类型  ：long
        Class<Void> voidClass = void.class; // ：void
        Class<Class> classClass = Class.class; // Class 类，万物皆对象 ：class java.lang.Class
    }
}






























