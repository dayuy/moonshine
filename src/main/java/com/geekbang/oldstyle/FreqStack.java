package com.geekbang.oldstyle;

import java.util.HashMap;
import java.util.Stack;

/**
 * 最大频率栈
 * // 从栈中删除并返回出现频率最高的元素
 * // 若频率最高的元素不止一个，则返回最近添加的那个
 * 1。 肯定要有一个变量 maxFreq 记录当前栈中最高频率是多少
 * 2。 得知道一个频率freq对应的元素有哪些，且这些元素要有时间顺序。
 * 3、 每次pop调用，每个val对应的频率会变化，所以需要维持一个映射记录每个val对应的freq
 * **/
public class FreqStack {
    int maxFreq = 0;
    HashMap<Integer, Integer> valToFreq = new HashMap<>();
    HashMap<Integer, Stack<Integer>> freqToVal = new HashMap<>();

    // 在栈中加入一个元素 val
    public void push(int val) {
        int freq = valToFreq.getOrDefault(val, 0) + 1;
        valToFreq.put(val, freq);
        freqToVal.putIfAbsent(freq, new Stack<>());
        freqToVal.get(freq).push(val);

        maxFreq = Math.max(maxFreq, freq);
    }


    public int pop() {
        Stack<Integer> val = freqToVal.get(maxFreq);
        int v = val.pop();

        int freq = valToFreq.get(v) - 1;
        valToFreq.put(v, freq);

        if (val.isEmpty()) {
            maxFreq--;
        }
        return v;
    }
}
