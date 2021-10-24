package com.geekbang.exercise.char04;

import java.util.*;

public class IntegerExercise {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        // int -> Integer
        // jdk5 前是手动装箱和拆箱
        int n1 = 100;
        // 手动装箱
//        Integer integer = new Integer(n1);
        Integer integer1 = Integer.valueOf(n1);
        System.out.println(integer1);

        // 手动拆箱 Integer -> int
        int i = integer1.intValue();
        System.out.println(i);

        // jsk5 后，就可以自动装箱和自动拆箱
        int n2 = 200;
        Integer integer2 = n2; // 底层对应的就是Integer.valueOf()
        int n3 = integer2; // 底层对应的就是 integer2.intValue()
        System.out.println(integer2 + "  " + n3);


        Object obj1 = true ? new Integer(1) : new Double(2.0);
        System.out.println(obj1); // 1.0 ???  因为三元运算符是一个整体，会提升精度，所以是1.0，而不是1

        int n10 = 1;
        if (n10 == 0 & n10++ != 3) {
            System.out.println("yes" + n10);
        }
        System.out.println("no" + " " + n10); // 2 ??？ 因为 & 即使前半部分false，也会执行后半部分

        Object obj2;
        if (true) {
            obj2 = new Integer(1);
        } else {
            obj2 = new Double(2.0);
        }
        System.out.println(obj2);

        // 包装类（Integer）-> String
        Integer n6 = 100;
        String s6 = n6 + "";
        String s7 = n6.toString();
        String s8 = String.valueOf(n6);

        // String -> Integer
        String s9 = "1234";
        Integer n7 = Integer.valueOf(s9);
        Integer n8 = Integer.parseInt(s9);
        Integer n9 = new Integer(s9);


        String a = "hsp"; // 指向常量池
        String b = new String("hsp"); // 指向堆中对象
        System.out.println(a.equals(b)); // Ture
        System.out.println(a==b); // False
        System.out.println(a==b.intern()); // T  intern()返回常量池地址
        System.out.println(b==b.intern()); // F


        Person p1 = new Person();
        p1.name = "hspedu";
        Person p2 = new Person();
        p2.name = "hspedu";

        System.out.println(p1.name.equals(p2.name)); // true  比较内容
        System.out.println(p1.name == p2.name); // true
        System.out.println(p1.name == "hspedu"); // true

        String s1 = new String("bcde");
        String s2 = new String("bcde");
        System.out.println(s1 == s2); // false
    }
}

class Person {
    public String name;

    public Person(){}

    public Person(String name) {
        this.name = name;
    }
}

class Test1 {
    String str = new String("hsp");
    final char[] ch = {'j', 'a', 'v', 'a'};
    public void change(String str, char ch[]) {
        str = "java";
        ch[0] = 'h';
    }

    public static void main(String[] args) {
        Test1 ex = new Test1();
        ex.change(ex.str, ex.ch);
        System.out.println(ex.str + " and "); // hsp and
        System.out.println(ex.ch); // hava

        String str = null;
        StringBuffer sb = new StringBuffer();
        sb.append(str);
        System.out.println(sb.length());

        String str1 = "abcdefg";
        try {
            str1 = reverseWord(str1, 5,4);
        } catch (Exception e) {
            System.out.println(e.getMessage());
//            return;
        }
        System.out.println(str1);

        collectionTest();
    }

    public static String reverseWord(String str, int start, int end) {
        if (!(str != null && start>=0 && end < str.length() && end > start)) {
            throw new RuntimeException("参数不争气");
        }
        char[] charArr = str.toCharArray();
        for (int i = start,j=end; i < j; i++, j--) {
            char temp = charArr[i];
            charArr[i] = charArr[j];
            charArr[j] = temp;
        }
        return new String(charArr);
    }

    public static void countStr(String str) {
        if (str == null) {
            System.out.println("输入不能为null");
            return;
        }
        int strLen = str.length();
        int numCount = 0;
        int lowerCount = 0;
        int upperCount = 0;
        for (int i = 0; i < strLen; i++) {
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                numCount++;
            } else if (str.charAt(i) >= 'a' && str.charAt(i) <= 'z') {
                lowerCount++;
            } else if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') {
                upperCount++;
            }
        }
        System.out.println(numCount + lowerCount + upperCount);
    }

    public static void collectionTest() {
        Collection col = new ArrayList();
        col.add("haha");
        col.add("lala");
        col.add("mama");

        System.out.println("col:" + col);
        // 迭代器的执行原理
        // 1、先得到col对应的迭代器
        Iterator iterable = col.iterator();
        // 2、使用while循环遍历，判断是否还有下一个元素
        while (iterable.hasNext()) {
            // 3、next()作用：下移；将下移后集合位置上的元素返回，类型是obj(编译类型)
            Object obj =  iterable.next();
            System.out.println(obj);
        }

        // 1、增强for，可以使用在Collection集合、数组中
        // 2、增强for，底层仍然是迭代器
        // 3、增强for可以理解为就是简化版的迭代器遍历
        for (Object book : col) {
            System.out.println("book=" + book);
        }


        // List 集合类中元素有序（即添加顺序和取出顺序一致）、且可重复
        List list = new ArrayList();
        list.add("jack");
        list.add("tom");
        list.add("mary");
        list.add("hsp");
        System.out.println("list=" + list);

        System.out.println(list.get(2));
        list.add(2, "lyna");
        list.set(2, "mali");

        List listbook = new ArrayList();
        listbook.add(new Book("三国", "罗贯中", 30));
        listbook.add(new Book("红楼", "曹雪芹", 90));
        listbook.add(new Book("水浒传", "施耐庵", 80));

        for (Object book : listbook) {
            System.out.println("book:" + book);
        }

        sort(listbook);

        for (Object book : listbook) {
            System.out.println("After book: " + book);
        }

        int[] a = new int[]{1,2,3};
        int[] b = Arrays.copyOf(a, 15);
        System.out.println(b);
    }

    public static void sort(List list) {
        int listSize = list.size();
        for (int i = 0; i < listSize - 1; i++) {
            for (int j = 0; j < listSize - 1 - i; j++) {
                Book book1 = (Book) list.get(j);
                Book book2 = (Book) list.get(j + 1);

                if (book1.getPrice() > book2.getPrice() ) {
                    list.set(j, book2);
                    list.set(j+1, book1);
                }
            }
        }
    }
}

class Book{
    private String name;
    private String author;
    private double price;

    public Book(String name, String author, double price) {
        this.name = name;
        this.author = author;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "name: " + name + "\t\tprice: " + price + "\t\tauthor:" + author;
    }
}
