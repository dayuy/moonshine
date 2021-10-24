package com.geekbang.exercise.char02;

public class Usbinterface {
    public static void main(String[] args) {
        InterfaceBB bb = new InterfaceBB();
        bb.start();

        // 2、把手机接入到计算机
        Computer computer = new Computer();
        computer.work(bb);

        System.out.println(bb.a);
        System.out.println(InterfaceBB.a);
        System.out.println(Usb.a);

        // 多态数组
        Usb[] usbs = new Usb[2];
        usbs[0] = new InterfaceBB();
        usbs[1] = new InterfaceBB();

        for (int i = 0; i < usbs.length; i++) {
            usbs[i].stop(); // 动态绑定

            // 调用类特有方法，需要进行向下转型
            if (usbs[i] instanceof InterfaceBB) {
                ((InterfaceBB) usbs[i]).call();
            }
        }

        AnonymousInnerClass a1 = new AnonymousInnerClass();
        a1.method();
    }
}

class InterfaceBB implements Usb {
    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    public void call() {}
}

class Computer {
    // 编写一个方法，让计算机工作
    public void work(Usb usb) {
        // 通过接口，来调用方法
        usb.start();
        usb.stop();
    }
}

class Outer02 { // 外部类
    private int n1 = 100;
    private void m2() {} // 私有方法
    public void m1() {
        // 1. 局部内部类是定义在外部类的局部，如 方法、代码块。
        // 3、不能添加访问修饰符，但是可以使用final修饰
        // 4、作用域：仅仅在定义它的方法或代码块中
        final class Inner02 {
            private int n1 = 800;
            // 2、可以直接访问外部类的所有成员，包含私有的
            public void f1() {
                // 5、局部内部类可以直接访问外部类的成员
                System.out.println("n1=" + n1);
                m2();
                // 7、如果外部类和局部内部类的成员重名时，默认遵循就近原则，如果想访问外部类的成员，使用外部类名.this.成员
                //    Outer02.this 本质就是外部类的对象。即哪个对象调用类m1，Outer02.this就是那个对象。
                System.out.println("n1=" + n1 + "外部类的n1=" + Outer02.this.n1);
                System.out.println("Outer02.this hashcode=" + Outer02.this); // 与 OuterO2 out = new OuterO2()中的out的hashcode一样
            }
        }

        // 6、外部类只能在方法中（作用域），可以创建Inner02对象，然后调用方法即可
        Inner02 inner02 = new Inner02();
        inner02.f1();
    }
}


class AnonymousInnerClass {
    public void method() {
        // 使用场景：基于接口的匿名内部类
        // 1、需求：想使用IA接口，并创建对象
        // 2、传统方式，是写一个类，实现该接口，并创建对象
        Tigger tigger1 = new Tigger();
        tigger1.cry();
        // 3、现要求：Tigger/Dog 类只是使用一次，后面不再使用
        // 4、可以使用匿名内部类来简化开发，而不用单独创建Tigger\Dog
        IA tigger = new IA() {
            @Override
            public void cry() {
                System.out.println("老虎咆哮。。。");
            }
        };
        tigger.cry();
        tigger.cry();
        // 5. tigger 的编译类型？ IA
        // 6。 tigger 的运行类型？就是匿名内部类 AnonymousInnerClass$1
        // 7、jdk底层在创建匿名内部类时，立即马上就创建了 实例，并且把地址返回给tigger
        /**
         * 底层会分配类名 AnonymousInnerClass$1
         * class AnonymousInnerClass$1 implements IA {
         *      @Override
         *      public void cry() {
         *          System.out.println("老虎咆哮。。。");
         *      }
         * }
         * **/
        System.out.println("tiger的运行类型=" + tigger.getClass()); // AnonymousInnerClass$1
        // 8、匿名内部类使用一次，就不能再使用
        // new AnonymousInnerClass$1(); 错误，匿名类已经不存在了
    }

    public void method2() {
        // 基于类的匿名内部类
        // 1、father编译类型 Father
        // 2、father运行类型 AnonymousInnerClass$2
        // 3、底层会创建匿名内部类
        // 4、同时也直接返回了对象
        Father father = new Father("jack"){
            @Override
            public void test() {
                System.out.println("匿名内部类重写了test方法");
            }
        };
        father.test();
        System.out.println(father.getClass()); // AnonymousInnerClass$2
    }

    public void method3() {
        // 当作实参，直接传递，简洁高效
        f1(new IA() {
            @Override
            public void cry() {
                System.out.println("当作实参，直接传递");
            }
        });
    }

    // 静态方法，行参是接口类型
    public static void f1(IA ia) {
        ia.cry();
    }
}

interface IA {
    public void cry();
}

class Tigger implements IA {
    @Override
    public void cry() {
        System.out.println("老虎咆哮。。。");
    }
}

class  Father{
    public String name;

    public Father(String name) {
        this.name = name;
    }

    public void test(){}
}

class Outer08 { // 外部类
    private int n1 = 10;
    public String name = "张三";
    private void yell() {
        System.out.println("say()....");
    };
    // 1、成员内部类，是定义在外部类的成员位置上
    // 2、可以添加任意访问修饰符（public、protected、默认、private），因为它的地位就是一个成员
    public class Inner08 { // 成员内部类
        private int salary = 1000;
        public void say() {
            // 可以直接访问外部类的所有成员，包含私有的
            System.out.println("n1= " + n1 + " name=" + name);
            yell();
        }
    }
    public void t1() {
        // 3. 使用成员内部类
        Inner08 inner08 = new Inner08();
        inner08.say();
        System.out.println(inner08.salary); // 私有属性可以在同一类中访问
    }
    public Inner08 getInner08Instance() {
        return new Inner08();
    }
}
class Outer008 {
    public void test(){
        Outer08 outer08 = new Outer08();
        // 外部其他类，使用成员内部类的方式：
        // 方式1、outer08.new Inner08()
        Outer08.Inner08 inner08 = outer08.new Inner08();
        // 方式2、调用 返回一个Inner08实例的方法
        outer08.getInner08Instance();


        // 外部其他类 使用静态内部类
        // 方式1: 因为静态内部类，是可以通过类名直接访问
        Outer10.Inner10 inner10 = new Outer10.Inner10();
        inner10.say();
        // 方式2：调用 返回静态类的方法
        Outer10.getInner10();
    }
}

class Outer10 {
    private int n1 = 10;
    private static String name = "张三";
    private void cary(){};
    // 静态内部类
    // 1、放在外部类的成员位置
    // 2、使用 static 修饰
    // 3、只能直接访问外部类的所有静态成员
    // 4、可以添加任意访问修饰符（public、protected、默认、private）
    // 5、作用域：同其他成员一样
    public static class Inner10{
        public void say() {
            System.out.println(name);
            // cary();
        }
    }
    public static Inner10 getInner10() {
        return new Inner10();
    }
}



