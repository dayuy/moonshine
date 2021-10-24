package com.geekbang.exercise.char04;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

@SuppressWarnings({"all"})
public class LinkedListTest {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.add(1);

        linkedList.add(2);
        linkedList.remove(1);

        System.out.println("LinkdList:" + linkedList);

        // Set接口
        // 1、以 Set 接口的实现类 HashSet 为例，讲解Set接口的方法
        // 2、Set 接口的实现类对象，不能存放重复的元素，可以添加null
        // 3、Set 接口对象存放数据是无序（添加和取出的顺序不一致，但每次取出的顺序是固定的）
        Set set = new HashSet();
        set.add("john");
        set.add("lucy");
        set.add("john");
        set.add(null);
        System.out.println("set=" + set);

        // 遍历
        // 方式1：使用迭代器（因为Set接口也是继承了Conllection，便有了iterator()）
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Object next =  iterator.next();
            System.out.println("next: " + next);
        }
        // 方式二：增强for
        for (Object s :set) {
            System.out.println("s:" + s);
        }
        // set不能用索引获取，所以不能用普通的for循环


        set = new HashSet();
        System.out.println("set=" + set);
        // 4. HashSet不能加入相同元素
        set.add("lucy");
        set.add("lucy"); // false 添加失败
        set.add(new Dog("tom"));
        set.add(new Dog("tom")); // true
        // 坑！！！
        set.add(new String("hsp"));
        set.add(new String("hsp")); // false 添加失败 ？？？
        // 看原码分析，两种情况被认为是相同的，不能重复添加
        /**
         *  a. HashSet底层是按照HashMap操作。其中 key 为 Set的值，value=PRESENT当初占位符 为 Map的value。
         *  b. table[i] = newNode(hash, key, value, null) (其中i是根据hashCode计算的：hashCode ^ hashCode >>> 16)
         *      如果hash相同，则table[i]是个链表，循环此链表进行比较，相同则替代，不同则追加到最后
         *  if (p.hash == hash && ((k=p.key) == key) || (key != null && key.equals(k))) { e = p}
         *  if (p instanceof TreeNode) {p.putTree(this, tab, hash, key, value)}
         *  if (循环链表比较，一样则跳出，不同则添加到最后。然后判断如果此链表达到8个，小于64，则扩容数组table，如果大于64则转成红黑树)
         * **/
        System.out.println((new String("hsp")).equals(new String("hsp"))); // true
        /**
         *  5、HashSet扩容机制：
         *  a、HashSet 底层是HashMap，第一次添加时，table数组扩容到16，临界值threshold是16*0.75=12
         *  b、如果到了12，则会扩容到16*2=32，新的临界值32*7.5=24。
         *  c、!!!!注意：这里不只是table的item数量，还包括每个item的链表内结点数（如table[i]内node总数的累积也会使table扩容）
         *
         */
        hashSetStructure();
    }
    public static void hashSetStructure() {
        // 模拟 数组-链表
        // 1、创建一个数组，数组类型是 Node[]
        Node[] table = new Node[6];
        // 3、创建结点
        Node john = new Node("john", null);
        table[2] = john;

        // 4、将jack挂到john下
        Node jack = new Node("jack", null);
        john.next = jack;

        System.out.println("table: " + table);
    }
}

class Dog{
    private String name;

    public Dog(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Node {
    Object item;
    Node next;

    public Node(Object item, Node next) {
        this.item = item;
        this.next = next;
    }
}
