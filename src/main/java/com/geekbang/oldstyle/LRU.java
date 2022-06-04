package com.geekbang.oldstyle;

import java.util.HashMap;

public class LRU {
    /**
     * LUR(least recent used) 缓存机制
     * 接收一个capacity参数作为缓存最大容量；
     * 实现两个API，一个是 put(key, val), get(key)，时间复杂度O(1)
     * 设计数据结构：
     * 1。显然 cache 中的元素必须有时序，
     * 2。要在 cache 中快速找到key，有返回val，无返回-1；
     * 3。每次访问 cache 中的某个key，需要变为最近使用的，支持任意位置快速插入和删除元素。
     * 哈希表查找快，但数据无固定顺序；链表有顺序，插入删除快，但查找慢。
     * 结合使用：哈希链表 LinkedHashMap
     * **/
}

class Node {
    public int key, val;
    public Node next, prev;
    public Node(int k, int v) {
        this.key = k;
        this.val = v;
    }
}

class DoubleList {
    private Node head, tail;
    private int size;

    public DoubleList() {
        // 初始化双向链表的数据
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    // 在链表尾部添加节点 x
    public void addLast(Node x) {
        x.prev = tail.prev;
        x.next = tail;
        tail.prev.next = x;
        tail.prev = x;
        size++;
    }

    // 删除链表中的 x 节点（x一定存在）
    // 由于是双链表且给的目标是Node节点，O(1)
    public void remove(Node x) {
        x.prev.next = x.next;
        x.next.prev = x.prev;
        size--;
    }

    public Node removeFirst() {
        if (head.next == tail) {
            return null;
        }
        Node first = head.next;
        remove(first);
        return first;
    }

    public int size() {
        return size;
    }
}

class LRUCache {
    private HashMap<Integer, Node> map;
    private DoubleList cache;
    private int cap;

    public LRUCache(int capacity) {
        this.cap = capacity;
        map = new HashMap<>();
        cache = new DoubleList();
    }

    private void makeRecently(int key) {
        Node x = map.get(key);
        // 先删除，再插入队尾
        cache.remove(x);
        cache.addLast(x);
    }

    private void addRecently(int key, int val) {
        Node x = new Node(key, val);
        cache.addLast(x);
        map.put(key, x);
    }

    private void deleteKey(int key) {
        Node x = map.get(key);
        cache.remove(x);
        map.remove(key);
    }

    // 删除最久未使用的元素
    private void removeLeastRecently() {
        Node deletedNode = cache.removeFirst();
        int deletedKey = deletedNode.key;
        map.remove(deletedKey);
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        makeRecently(key);
        return map.get(key).val;
    }

    public void put(int key, int val) {
        if (map.containsKey(key)) {
            deleteKey(key);
            addRecently(key, val);
            return;
        }

        if (cap == cache.size()) {
            removeLeastRecently();
        }
        addRecently(key, val);
    }
}
