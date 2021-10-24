package com.geekbang.exercise.char04;

import java.util.HashSet;
import java.util.Objects;

public class HashSetTest{
    public static void main(String[] args) {
        /**
         *  定义一个Employee类，包含private成员属性name、age。
         *  要求：创建3个Employee对象放入HashSet中，
         *  当name和age的值相同时，认为是相同员工，不能添加到HashSet中
         * **/

        HashSet hashSet = new HashSet();
        hashSet.add(new Employee("milan", 20));
        hashSet.add(new Employee("smith", 28));
        hashSet.add(new Employee("milan", 20));

        System.out.println("hashSet= " + hashSet);

        // 示例题
        HashSet hashSet1 = new HashSet();
        Employee aa = new Employee("AA", 20);
        Employee bb = new Employee("BB", 21);
        hashSet1.add(aa);
        hashSet1.add(bb);
        aa.setName("CC");
        // aa 并没有被删掉，因为aa.name已经变成了CC，再次计算的hashcode已经改变设为c，定位不到原来的位置a。a位置上的值name已被改为CC
        hashSet1.remove(aa); //[Employee{name='CC', age=20}, Employee{name='BB', age=21}]
        // 可以被添加，因为根据name和age计算得到的hashcode为c，table上c的位置还是空
        // [Employee{name='CC', age=20}, Employee{name='CC', age=20}, Employee{name='BB', age=21}]
        hashSet1.add(new Employee("CC", 20));
        // 可以被添加，因为根据name和age计算得到的hashcode为a，table上a的位置已经有值，但name为CC。这里equals不相等，被添加到链尾。
        hashSet1.add(new Employee("AA", 20));
        System.out.println(hashSet1);

    }
}

class Employee{
    private String name;
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return age == employee.age && Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}

