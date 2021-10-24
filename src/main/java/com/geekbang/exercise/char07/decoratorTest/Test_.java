package com.geekbang.exercise.char07.decoratorTest;

public class Test_ {
    public static void main(String[] args) {
        BufferedReader_ bufferedReader_ = new BufferedReader_(new FileReader_());
        bufferedReader_.readFiles(10);
        bufferedReader_.readFile();

        BufferedReader_ bufferedReader_1 = new BufferedReader_(new StringReader_());
        bufferedReader_1.readString();
        bufferedReader_1.readStrings(5);
    }
}
