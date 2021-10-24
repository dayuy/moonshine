package com.geekbang.exercise.char05;

import org.junit.Test;

import java.util.ArrayList;

public class GenaricTest {
    public static void main(String[] args) {
        ArrayList<Dog> dogs = new ArrayList<Dog>();
        dogs.add(new Dog());
//        dogs.add(new Cat());

        for (Dog dog : dogs) {
            System.out.println(dog.getClass());
        }


        // 1、给泛型指定数据类型时，要求是引用类型，不能是基本数据类型
        ArrayList<Integer> list = new ArrayList<Integer>(); // OK
        // ArrayList<int> ints = new ArrayList<int>(); // Error
        // 2、给泛型指定具体类型后，可以传入给类型或该类型的子类类型
        Animal<Cat> cat1 = new Animal<>(new Cat()); // 编译的时候可以类型推断
        Animal<Cat> whiteCat = new Animal<>(new WhiteCat());
        cat1.f();
        whiteCat.f();

    }

    @Test
    public void m1() {
        System.out.println("test m1");
    }
}

class Dog {}
class Cat {}
class WhiteCat extends Cat{}
class Animal<E> {
    E e;

    public Animal(E e) {
        this.e = e;
    }

    public void f() {
        System.out.println(e.getClass());
    }

    public<T> void j(T t) {}
}
