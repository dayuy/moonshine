package com.geekbang.exercise.char07.decoratorTest;

/**
 * 做成处理流/包装流 装饰器模式
 * **/
public class BufferedReader_ extends Reader_{
    private Reader_ reader_;
    // 构造器 接收Reader_子类，赋予子类的基础方法
    public BufferedReader_(Reader_ reader_) {
        this.reader_ = reader_;
    }
    // 让方法更加灵活，可扩展，以reader_的基础方法做定制
    public void readFiles(int num) {
        for (int i = 0; i < num; i++) {
            reader_.readFile();
        }
    }
    // 扩展构 造器参数子类 readString方法，批量处理字符串数据
    public void readStrings(int num) {
        for (int i = 0; i < num; i++) {
            reader_.readString();
        }
    }
}
