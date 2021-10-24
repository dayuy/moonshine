package com.geekbang.syntax;

public class Char01 {
    public static void main(String[] args) {
        char c1 = 'a';
        char c2 = '\t';
        char c3 = '韩';
        char c4 = 97; // 字符类型可以直接存放数字

        System.out.println(c4); // 当输出时，会输出 97 表示的字符 =》 编码

        char c5 = 'a';
        System.out.println(c5);
        System.out.println((int) c5);
        char c6 = '韩';
        System.out.println((int) c6);
        char c7 = 38889;
        System.out.println(c7);

        // char类型可以进行运算，相当于一个整数，因为有对应的unicode编码
        System.out.println('a' + 10); // 107

        char c8 = 'b' + 1;
        System.out.println((int) c8);
        System.out.println(c8); // c


        int c9 = 'a';
        double c10 = 80;
        System.out.println(c9); // 97
        System.out.println(c10); // 80.0

        // 系统会首先自动将所有数据类型转换成容量最大的那种数据类型，然后再进行计算
        int c11 = 10;
//        float c12 = c11 + 1.1;  错误 1.1默认是最大的数据类型 double。不能转成小的精度float
        float c12 = c11 + 1.1F;
        double c13 = c11 + 1.1;

//        int c14 = 1.1; // 错误， double 不能转成精度小的int

        byte b1 = 10;  // 4. 把具体数付给byte时，先判断是否在（+-128）之间，是的话可以，否则不能把int降级为byte。
//        byte b3 = 1000; //错

//        3. char 与 （byte 、short）不能相互转换
//        char b2 = b1;

//        5. byte、char、short 可以相互计算的，在计算时首先转换为int。
        byte b2 = 1;
        byte b3 = 2;
        short s1 = 1;
//        short s2 = b2 + s1; 错误，int 不能赋给short
//        short s3 = b2 + b3;
        int s2 = b2 + s1;

//        6. boolean 不参与转换
        boolean pass = true;
//        int num2 = pass;

//        7. 自动提升原则
    }
}

class ForceCovert {
    public static void main(String[] args) {
        // 强制类型转换
        int n1 = (int) 1.9;
        System.out.println("n1=" + n1);

        int n2 = 2000;
        byte b1 = (byte) n2;
        System.out.println("b1=" + b1);

        int x = (int) (10 * 3.5 + 6 * 1.5);
        System.out.println(x);
    }
}

class StringToBasic {
    public static void main(String[] args) {
        int n1 = 100;
        float f1 = 1.1F;
        double d1 = 4.5;
        boolean b1 = true;
        String s1 = n1 + "";
        String s2 = f1 + "";
        String s3 = d1 + "";
        String s4 = b1 + "";
        System.out.println(s1 + " " + s2 + " " + s3 + " " + s4);


        // String -> 基本类型(使用基本类型的包装类的相应方法，得到数据的基本类型)
        String s5 = "123";
        int r1 = Integer.parseInt(s5);
        double r2 = Double.parseDouble(s5);
        float r3 = Float.parseFloat(s5);
        long r4 = Long.parseLong(s5);
        byte r5 = Byte.parseByte(s5);
        boolean r6 = Boolean.parseBoolean(s5);
        short r7 = Short.parseShort(s5);
        System.out.println(r1 + " " + r2 + " " + r3 + " " + r4 + " " + r5 + " " + r6 + " " + r7);

        // 字符串写出char，是指 把字符串的第一个字符得到
        System.out.println(s5.charAt(0));

        char c1 = '男';
        char c2 = '女';
        System.out.println(c1 + c2); //char 52906

        double price1 = 123.56;
        double price2 = 100.11;
        System.out.println(price1 + price2); // 223.67000002
    }
}

class arithmeticOperator {
    public static void main(String[] args) {
        System.out.println(10 / 4); // 2
        System.out.println(10.0 / 4); // 2.5

        double d = 10 / 4; // 2.0
        System.out.println(d);

        // % 的本质是 a % b = a - a/b*b
        System.out.println(10 % 3);
        System.out.println(-10 % 3);
        System.out.println(10 % -3);
        System.out.println(-10 % -3);

        //++ 的使用
        int i = 10;
        i++; // 独立使用，等价于 i = i+1; => i = 11
        ++i; // 独立使用，等价于 i = i+1; => i = 12
        System.out.println("i=" + i);

        // ++ 作为表达式使用，前++：先自增后赋值；后++先赋值后自增
        int j = 8;
//        int k = ++j; // j=j+1; k=j; 9 9
        int k = j++; //  k = j; j = j+1; 8 9
        System.out.println("k=" + k + "j=" + j);

        int e = 1;
        e = e++; // 1) temp = i; 2) i = i + 1 = 2  3) i = temp;
        System.out.println(e); // 1

        int f = 1;
        f = ++f; // 1) f=f+1  2) temp = f 3) f = temp
        System.out.println(f); // 2


        // 逻辑与 &  ：如果第一个条件为false，后面依然会判断
        // 短路 && 区别 ：如果第一个条件为false，后面不会再判断
        int a = 4;
        int b = 9;
        if (a < 1 & ++b < 50) {
            System.out.println("ok300");
        }
        System.out.println("a=" + a + " b=" + b); // 4 10

        if (a < 1 && ++b < 50) {
            System.out.println("ok400");
        }
        System.out.println("a=" + a + "b=" + b); // 4 9

        // 逻辑异或
        boolean c = (10 > 1) ^ (3 < 5);
        System.out.println("b=" + c);

        // 复合赋值运算符会进行类型转换
        byte g = 3;
        g += 2; // 等价于 b = (byte)(b + 2);
        g++; // 等价于 g = (byte)(g+1)
//        g = g + 2; // 报错: int 类型不能赋值给 byte类型
        System.out.println(g);


        int a1 = 10;
        int b1 = 99;
        int result = a1 > b1 ? a1++ : b1--;
        System.out.println("result=" + result); // 99
        System.out.println(~a1);

        int b12 = 66;
        System.out.println(-10.5 % 3); // -10.5 - (int)(-10.5/3*3) = -1.5 (小数是个近似值)
        System.out.println(++b12 + b12); // 67+67


        // 进制
        int n1 = 0b1010; // 二进制
        int n2 = 1010; // 十进制
        int n3 = 01010; // 八进制
        int n4 = 0x10101; // 十六进制
        System.out.println(n1);
        System.out.println(n2);
        System.out.println(n3);
        System.out.println(n4);

        // n转十进制（n为2，8，16）
        // ：从最低位，将每个位数提取出来乘以n的（位数-1）次方
        // 二进制-> 十进制 0b1011 = 1*(2**0) + 1*(2**1) + 0*(2**2) + 1*(2**3)
        // 八进制-> 十进制 0234 = 4*(8**0) + 3*(8**1) + 2*(8**2)
        // 十六进制 -> 十进制 0x23A = 10*(16*0) + 3*(16*1) + 2*(16*2)

        // 十进制转n进制（n为2，8，16）
        // : 将该数不断除以 n，直到商为0为止，然后将每步得到的余数倒过来，就是对应的n进制。
        // 十进制 -> 二进制 34： 34 / 2 = 17 余 0，17 / 2 = 8 余 1， 8 /2 = 4 余 0， 4/2 = 2 余0，2/2=1 余0 --> 100010（0b00100010）
        // 十进制 -> 八进制 131： 131 / 8 = 16余 3， 16/8=2 余0，--> 203（0203）
        // 十进制 -> 十六进制 8912: 8912 / 16 = 557 余0，557/16 = 34 余13, 34/16 = 2 余 2 --> d（0x22D）

        // 二进制转八进制
        // ： 从低位开始，将二进制数每三位一组，转成对应的八进制数就好（因为三个二进制刚好凑成八进制的一位：000 -> 0, 111 -> 7）
        // ob11010101 => 11(3)010(2)101(5) => 0325

        // 二进制转16进制
        // ： 从低位开始，经二进制每四位一组，转成对应的十六进制。（因为二进制的四位数刚好凑成16进制的一个单位：0000 -> 0， 1111 -> 15）
        // ob11010101 => 1101(D)0101(5) => 0xD5

        // 八进制转二进制
        // ： 将八进制的每1位，转成对应的二进制的三位数
        // 0237 => 2(010)3(011)7(111) => 1001 1111（0b10011111）

        // 十六进制转二进制
        // : 将十六进制的每1位，转成对应的二进制的四位数
        // 0x23B => 2(0010)3(0011)B(1011) => 0010 0011 1000 (0b001000111011)


        // 位运算
        int w1 = 1 >> 2; // 算术右移  00000001 -> 00000000 低位溢出，符号位不变，并用符号位补溢出的高位。（其实就是 除以2再除以2）
        int w2 = -1 >> 2; //         10000001 -> 10000001
        int w3 = 1 << 2; // 算术左移  00000001 -> 00000100 符号位不变，低位补0 （本质就是 乘以2再乘以2）
        int w4 = -1 << 2;  //       10000001 -> 10000100
        int w5 = 3 >>> 2; // 无符号右移 00000010 -> 00000000  低位溢出，高位补0
        System.out.println(1 >> 2); // 0
        System.out.println(1 << 2); // 4
        System.out.println(4 << 3); // 4 * 2 * 2 * 2 = 32
        System.out.println(15 >> 2); // 15 / 2 / 2 = 3
        System.out.println(3 >>> 2); // 3 / 2 / 2 = 0
        System.out.println(-1 >> 2); // ??  = -1
        System.out.println(-1 << 2); // - 1 * 2 * 2 = -4

        int w6 = ~2; // 按位取反
        int w7 = 2 & 3; // 2按位与3
        int w8 = 2 | 3; // 按位或
        int w9 = -3 ^ 3; // 按位异或


        // 推导 ～-2
        // 1。先得到-2的原码 10000000 00000000 00000000 00000010  最高位表示正负，1表示负，0表示正
        // 2。-2 的反码     11111111 11111111 11111111 11111101  负数的反码=符号位不变，其他位取反
        // 3。-2 的补码     11111111 11111111 11111111 11111110  负数的补码=其反码 + 1           // 为什么要转成补码进行运算：因为计算机都是以补码的方式运算的
        // 4。~-2 运算后补码 00000000 00000000 00000000 00000001  按位取反 0->1,1->0
        // 5. 运算后的原码   00000000 00000000 00000000 00000001 => 1  正数的补码原码反码三合一       // 为什么运算完后，又要转成原码？因为我们看运算结果时，要看它的原码
        System.out.println(~-2); // 1

        // 推导 2&3
        // 1. 2的原码      00000000 00000000 00000000 00000010
        //    2的补码      00000000 00000000 00000000 00000010
        // 2。3的原码      00000000 00000000 00000000 00000011
        //    3的补码      00000000 00000000 00000000 00000011
        // 3。按位与 &      00000000 00000000 00000000 00000011 & 00000000 00000000 00000000 00000011
        //       运算后补码  00000000 00000000 00000000 00000010
        //       运算后原码  00000000 00000000 00000000 00000010 => 2
        System.out.println(2 & 3); // 2
    }
}

class Char06OOP {
    public static void main(String[] args) {
        class Cat {
            // 属性
            String name;
            int age;
            String color;

            // 行为
            public void speak() {
                System.out.println("我要说话");
            }
        }

        Cat cat1 = new Cat();
        cat1.name = "小贝";
        cat1.age = 100;
        cat1.color = "花色";
        System.out.println("cat1的信息" + cat1.name + " " + cat1.age + " " + cat1.color);
        cat1.speak();

        byte a = 2;
        char c = 'a';
        int b = a;

    }
}

// 递归
class Recusion {
    public static void main(String[] args) {
        class Tower {
            // num 表示要移动的个数，a,b,c分别表示A塔、B塔、C塔
            public void move(int num, char a, char b, char c) {
                // 如果只有一个盘，直接从A移到C
                if (num == 1) {
                    System.out.println(a + "->" + c);
                } else {
                    // 如果有多个盘，可以看成两个，最下面的一个，和上面所有的为一个
                    // 1》将上面所有的移到b，借助c
                    move(num - 1, a, c, b);
                    // 2》把最下面的盘，移到c
                    System.out.println(a + "->" + b);
                    // 3》再把b塔的所有的移到c，借助a
                    move(num - 1, b, a, c);
                }
            }
        }

        // 汉诺塔
        // 把所有的盘从A移动到C，一次只能移动一个盘，且小的不能在大的下面被压。
        Tower tower = new Tower();
        tower.move(10, 'A', 'B', 'C');
    }
}

class Qualifier {
    // 四个属性，分别使用不同的访问修饰符来修饰
    public int n1 = 100;
    protected int n2 = 100;
    int n3 = 300;
    private int n4 = 400;

    public void m1() {
        // 该方法可以访问四个属性
        System.out.println(n1 + n2 + n3 + n4);
    }
}

class Qualifier01 {
    public void say(){
        Qualifier q = new Qualifier();
        // 在同一个包下，可以访问public、protected、默认；不能访问private
        System.out.println(q.n1 + q.n2 + q.n3);
    }
}

// 多态 polymorphic
class Base {
    int count = 10;
    public void display() {
        System.out.println(this.count);
    }
}
class Sub extends Base{
    int count = 20;
    public void display() {
        System.out.println(this.count);
    }
}
class PolymorphicExercise {
    public static void main(String[] args) {
        Sub s = new Sub();
        System.out.println(s.count); // 20
        s.display(); // 20

        Base b = s;
        System.out.println(b == s); // Ture
        System.out.println(b.count); // 10
        b.display(); // 20
    }
}

class BindA {
    public int i = 10;
    public int sum() {
        return get1() + 10;
    }
    public int sum1() {
        return i + 10;
    }
    public int get1() {
        return i;
    }
}

class BindB extends BindA {
    public int i = 20;
//    public int sum() {
//        return i + 20;
//    }
    public int get1() {
        return i;
    }
//    public int sum1() {
//        return i + 10;
//    }
}

class DynamicBinding{
    public static void main(String[] args) {
        BindA a = new BindB();
        System.out.println(a.sum());
        System.out.println(a.sum1());
    }
}





