package com.geekbang.oldstyle;

import io.swagger.models.auth.In;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Structure {
}

/**
 * 链表
 * **/





/**
 * 单调队列结构解决滑动窗口
 * leetcode 239 滑动窗口最大值
 * ：给你输入一个数组 nums 和一个正整数 k，有一个大小为 k 的窗口在nums上从左到右滑动，请输出每次窗口中k个元素的最大值
 * 要求：时间复杂度 O(1)
 * **/
class Struct_LinkedList {
    public static void main(String[] args) {
        int[] nums = new int[]{1,3,-1,-3,5,3,6,7};
        // 希望输出 [3,3,5,5,6,7]
        int[] res = maxSlideWindow(nums, 3);
        System.out.println(res.length); // 如何打印一个数组（再过一遍java的数据结构把）
    }

    public static int[] maxSlideWindow(int[] nums, int k) {
        MonotonicQueue mq = new MonotonicQueue();
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) {
                mq.push(nums[i]);
            } else {
                mq.push(nums[i]);
                res.add(mq.max());
                mq.pop(nums[i - k + 1]);
            }
        }

        // 将 List 类型转化为 int[] 数组作为返回值
        int[] arr = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            arr[i] = res.get(i);
        }
        return arr;
    }
}

class MonotonicQueue {
    // 要支持头部和尾部 增删元素，就选双链表
    private LinkedList<Integer> q = new LinkedList<>();

    void push(int n) {
        // 将前面小于自己的元素都删除
        while (!q.isEmpty() && q.getLast() < n) {
            q.pollLast();
        }
        q.addLast(n);
    }

    int max() {
        return q.getFirst();
    }

    void pop(int n) {
        if (n == q.getFirst()) {
            q.pollFirst();
        }
    }
}




































