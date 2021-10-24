package com.geekbang.syntax;

public class Char02 {
    public static void main(String[] args) {

        int a = 10;
        double b = 10.0;
        System.out.println(a == b); // true. 基本类型只判断值

        Integer integer1 = new Integer(1000);
        Integer integer2 = new Integer(1000);
        System.out.println(integer1 == integer2); // false
        integer1.equals(integer2);  // true

        String str1 = new String("hhhhhh");
        String str2 = new String("lllll");
        System.out.println(str1 == str2); // false
        str2.equals(str1); // true

        Base base1 = new Base();
        Base base2 = base1;
        base2.equals(base1); // false

        Person p1 = new Person("javk", 20, '男');
        Person p2 = new Person("javk", 20, '男');
        p2.equals(p1); // true 重写来equals方法
        System.out.println(p1.hashCode() + "  " + p2.hashCode());
        System.out.println(base1.hashCode() + "  " + base2.hashCode());

        System.gc(); // 主动触发垃圾回收
    }

    /**
     * public boolean equals(Object obj) {
     *         if (obj instanceof Integer) {
     *             return value == ((Integer)obj).intValue();
     *         }
     *         return false
     *     }
     * **/

    static class Person{
        private String name;
        private int age;
        private char gender;

        public Person(String name, int age, char gender) {
            this.name = name;
            this.age = age;
            this.gender = gender;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", gender=" + gender +
                    '}';
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Person) {
                Person p = (Person) obj;
                return this.name.equals(p.name) && this.age == p.age && this.gender == p.gender;
            }
            return false;
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

        public char getGender() {
            return gender;
        }

        public void setGender(char gender) {
            this.gender = gender;
        }
    }
}
