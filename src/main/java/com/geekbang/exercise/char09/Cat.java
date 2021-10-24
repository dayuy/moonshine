package com.geekbang.exercise.char09;

public class Cat {
    private String name = "招财猫";
    public int age = 2;

    public Cat() {}
    public Cat(int age) {
        this.age = age;
    }

    public void hi() {

//        System.out.println("Cat: hi" + name);
    }
    public void cry() {
        System.out.println("Cat: cry" + name);
    }
}
