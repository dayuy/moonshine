package com.geekbang.oldstyle;

import com.github.jknack.handlebars.internal.antlr.atn.PredictionMode;

import java.util.HashMap;
import java.util.LinkedHashSet;

public class LFUCache {
    // key - value
    HashMap<Integer, Integer> keyToVal;
    // key - freq
    HashMap<Integer, Integer> keyToFreq;
    // freq - k列表
    HashMap<Integer, LinkedHashSet<Integer>> freqToKeys;
    // 记录最小的频次
    int minFreq;
    // 记录 LFU 缓存的最大容量
    int cap;

    public LFUCache(int capacity) {
        keyToVal = new HashMap<>();
        keyToFreq = new HashMap<>();
        freqToKeys = new HashMap<>();
        this.cap = capacity;
        this.minFreq = 0;
    }

    public int get(int key) {
        if (!keyToVal.containsKey(key)) {
            return -1;
        }
        // 增加 key 对应的freq
        increaseFreq(key);
        return keyToVal.get(key);
    }

    public void put(int key, int val) {
        if (this.cap <= 0) return;

        if (keyToVal.containsKey(key)) {
            keyToVal.put(key, val);
            increaseFreq(key);
            return;
        }

        // key 不存在则插入
        // cap满的话，删除一个freq最小的key
        if (this.cap <= keyToVal.size()) {
            removeMinFreqKey();
        }

        keyToVal.put(key, val);
        keyToFreq.put(key, 1);
        freqToKeys.putIfAbsent(1, new LinkedHashSet<>());
        freqToKeys.get(1).add(key);

        // 插入新的key后，最小的freq肯定是1
        this.minFreq = 1;
    }

    private void increaseFreq(int key) {
        int freq = keyToFreq.get(key);
        keyToFreq.put(key, freq + 1);
        freqToKeys.get(freq).remove(key);
        freqToKeys.putIfAbsent(freq + 1, new LinkedHashSet<>());
        freqToKeys.get(freq + 1).add(key);
        if (freqToKeys.get(freq).isEmpty()) {
            freqToKeys.remove(freq);
            if (freq == this.minFreq) {
                this.minFreq++;
            }
        }
    }

    private void removeMinFreqKey() {
        LinkedHashSet<Integer> keyList = freqToKeys.get(this.minFreq);
        // 最先被插入的那个key就是该被淘汰的key
        int deletedKey = keyList.iterator().next();
        keyList.remove(deletedKey);
        if (keyList.isEmpty()) {
            freqToKeys.remove(this.minFreq);
        }
        keyToVal.remove(deletedKey);
        keyToFreq.remove(deletedKey);
    }

}
