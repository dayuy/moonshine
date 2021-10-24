package com.geekbang.polyparameter;

public class PolyParameter {
    // 多态的练习
    public static void main(String[] args) {
        Worker tom = new Worker("tom", 2500);
        Manager milan = new Manager("milan", 5000, 200000);
        PolyParameter polyParameter = new PolyParameter();
        polyParameter.showEmpAnnual(tom);
        polyParameter.showEmpAnnual(milan);

        polyParameter.testWork(tom);
        polyParameter.testWork(milan);

    }

    public void showEmpAnnual(Employee e) {
        System.out.println(e.getAnnual()); // 动态绑定方法
    }

    public void testWork(Employee e) {
        if (e instanceof Worker) {
            ((Worker) e).work(); // 向下转型
        } else if (e instanceof Manager) {
            ((Manager) e).manage(); // 向下转型
        }
    }
}
