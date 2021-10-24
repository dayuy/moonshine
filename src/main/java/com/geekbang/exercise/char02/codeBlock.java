package com.geekbang.exercise.char02;

public class codeBlock {
    public static void main(String[] args) {
        // 类被加载的举例
        // 1。 创建BB对象时
        BB b = new BB();

        // 2. 创建子类对象实例，父类也会被加载,且父类先被加载(但父类被加载过，不会再执行，即只执行一次)
        AA a = new AA();

        // 3. 调用静态方法时。（同样父类被加载过，不会再执行，即只执行一次）
        System.out.println(AA.n1);

        /**
         * BB 的static 代码块
         * AA 的静态代码块被执行
         * 999
         * **/
    }

}

class BB {
    static {
        System.out.println("BB 的static 代码块");
    }
}

class AA extends BB {
    public static int n1 = 999;
    static {
        System.out.println("AA 的静态代码块被执行");
    }
}
