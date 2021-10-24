package com.geekbang.game.cpu;

public class CpuNum {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        // 获取当前电脑cpu数量
        int cpuCores = runtime.availableProcessors();
        long m = runtime.totalMemory();
        System.out.println(cpuCores + " " + m);
    }
}
