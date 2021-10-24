package com.geekbang.exercise.char03;

public class Enumeration {
    public static void main(String[] args) {
        System.out.println(Season.AUTUMN);
        System.out.println(Season.SPRING);
        System.out.println(Season.WINTER);

        System.out.println(Season2.SPRING);
        System.out.println(Season2.WINTER);

        Season2 spring = Season2.SPRING;
        System.out.println(spring.name());
        System.out.println(spring.ordinal());
        Season2[] values = Season2.values();
        for (Season2 season : values) {
            System.out.println(season);
        }

        // valueOf: 将字符串转换成枚举对象，要求字符串必须为已有常量。否则报错
        System.out.println(Season2.valueOf("SPRING"));

        // compareTo
        /**
         * public final int compareTo(E o) {
         *     return self.ordinal - other.ordinal;
         * }
         * **/
        System.out.println(Season2.WINTER.compareTo(Season2.SPRING));
    }
}

// 自定义枚举实现
class Season {
    private String name;
    private String desc;

    // 4、定义四个对象
     public static final Season SPRING = new Season("春天", "warm");
     public static final Season WINTER = new Season("冬天", "cold");
     public static final Season AUTUMN = new Season("秋天", "soft");
     public static final Season SUMMER = new Season("夏天", "hot");

    // 1、将构造器私有化，防止 直接new
    // 2、去掉setXXX方法，防止属性被修改
    // 3、在Season内部，直接创建固定的对象
    private Season(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}

// 使用 enum 来实现枚举
// 1、使用关键字 enum代替class
// 2、常量名(实例参数)，且写在最前面
// 3、若有多个常量，使用,隔开
enum Season2 {
    // 实际是调用构造器
    SPRING("春天", "寒冷"), WINTER("冬天", "hanm");
    private String name;
    private String desc;

    Season2(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }
}

class Exception {
    public void main(String[] args) {
        test();
    }

    public void test() {
        int num1 = 10;
        int num2 = 0;
        try {
            int res = num1 / num2;
        } catch (NullPointerException e) {
            e.getMessage();
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }

        System.out.println("程序继续执行。。。。");
    }
}
