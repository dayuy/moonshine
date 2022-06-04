package com.geekbang.oldstyle;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

/**
 * 栈 Stack：先进后出的逻辑顺序，符合某些问题的特点，比如说函数调用栈
 * 单调栈：实际是栈，利用巧妙的逻辑，使得每次新元素入栈后，栈内的元素都保持有序（单调递增或递减）
 * **/
public class Stack_test {
    public static void main(String[] args) {

    }
    // 给你一个数组 nums，请你返回一个等长的结果数组，结果数组中对应索引存储着下一个更大元素，如果没有更大的元素，就存 -1
    // 输入一个数组 nums = [2,1,2,4,3]，你返回数组 [4,2,4,-1,-1]
    public Vector<Integer> nextGreaterElement(Vector<Integer> nums) {
        Vector<Integer> res = new Vector<>(nums.size());
        Stack<Integer> s = new Stack<>();

        for (int i = nums.size(); i >= 0; i--) {
            while (!s.isEmpty() && s.firstElement() <= nums.get(i)) {
                s.pop();
            }

            res.set(i, s.empty() ? -1 : s.firstElement());
            s.push(nums.get(i));
        }
        return res;
    }
}

/**
 * 队列是一种先进先出的数据结构，栈是一种先进后出的数据结构
 * 底层都是数组或者链表实现的。
 * **/
// 用栈实现队列
class MyQueue {
    // 借助两个栈实现
    private Stack<Integer> s1, s2;

    public MyQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }
    // 添加元素到队尾
    public void push(int x) {
        s1.push(x);
    }

    // 删除队头的元素并返回
    public int pop() {
        peek();
        return s2.pop();
    }

    // 返回队头元素
    public int peek() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        return s2.peek();
    }

    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
}

// 用队列实现栈（只需用一个队列）
class MyStack {
    Queue<Integer> q = new LinkedList<>();
    int top_elem = 0;

    // 添加元素到栈顶
    public void push(int x){
        q.offer(x);
        top_elem = x;
    }

    // 删除栈顶的元素并返回
    public int pop(){
        int size = q.size();
        while (size > 1) {
            q.offer(q.poll());
            size--;
        }
        top_elem = q.peek();
        q.offer(q.poll());
        return q.poll();
    }

    // 返回栈顶元素
    public int top(){
        return top_elem;
    }

    public boolean empty(){
        return q.isEmpty();
    }
}

// 前缀和 - 数组
// 例：计算数组区间内元素和
class NumArray {
    private int[] preNums;

    public NumArray(int[] nums) {
        preNums = new int[nums.length + 1];

        for (int i = 1; i < preNums.length; i++) {
            preNums[i] = preNums[i-1] + nums[i-1];
        }

    }

    public int sumRange(int left, int right) {
        return preNums[right] - preNums[left];
    }
}

// 差分数组
// 我给你输入一个数组 nums，然后又要求给区间 nums[2..6] 全部加 1，再给 nums[3..9] 全部减 3，再给 nums[0..4] 全部加 2，最后 nums 数组的值
class DifferenceArray {
    private int[] diff;

    public DifferenceArray(int[] nums) {
        diff = new int[nums.length];
        diff[0] = nums[0];
        for (int i = 1; i < diff.length; i++) {
            diff[i] = nums[i] - diff[i-1];
        }
    }

    // 给闭区间[i,j]增加 val (可以为负数)
    public void increment(int i, int j, int val) {
        diff[i] = diff[i] + val;
        if (j + 1 < diff.length) {
            diff[j] = diff[j] - val;
        }
    }

    public int[] result() {
        int[] res = new int[diff.length];
        res[0] = diff[0];
        for (int i = 1; i < res.length; i++) {
            res[i] = res[i-1] + diff[i];
        }

        return res;
    }
}
