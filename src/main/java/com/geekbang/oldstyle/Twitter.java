package com.geekbang.oldstyle;

import java.util.*;

public class Twitter {
    private static int timestamp = 0;

    private HashMap<Integer, User> userMap = new HashMap<>();

    // 发布一条tweet
    public void postTweet(int userId, int tweetId) {
        if (!userMap.containsKey(userId)) {
            userMap.put(userId, new User(userId));
        }
        User u = userMap.get(userId);
        u.post(tweetId);
    }

    // 返回该user关注的人最近的动态id，（十条 由新到旧排序）
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new ArrayList<>();
        if (!userMap.containsKey(userId)) return res;
        Set<Integer> users = userMap.get(userId).followed;
        PriorityQueue<Tweet> pq = new PriorityQueue<>(users.size(), (a, b) -> (b.time - a.time));
        for (int id : users) {
            Tweet twt = userMap.get(id).head;
            if (twt == null) continue;
            pq.add(twt);
        }

        while (!pq.isEmpty()) {
            if (res.size() == 10) break;
            Tweet twt = pq.poll();
            res.add(twt.id);
            if (twt.next != null) {
                pq.add(twt.next);
            }
        }
        return res;
    }

    // 关注
    public void follow(int followerId, int leaderId) {
        if (!userMap.containsKey(followerId)) {
            User u = new User(followerId);
            userMap.put(followerId, u);
        }
        if (!userMap.containsKey(leaderId)) {
            User u = new User(leaderId);
            userMap.put(leaderId, u);
        }
        userMap.get(followerId).follow(leaderId);
    }

    // 取关
    public void unfollow(int followerId, int leaderId) {
        if (userMap.containsKey(followerId)) {
            User flwer = userMap.get(followerId);
            flwer.unfollow(followerId);
        }
    }
}

// 每个Tweet实例，记录自己的tweetId 和 发表时间 time
// 且是链表节点要有个next指针
class Tweet {
    protected int id;
    protected int time;
    protected Tweet next;

    public Tweet(int id, int time) {
        this.id = id;
        this.time = time;
        this.next = null;
    }
}

// User存储userId,
// 关注列表（HashSet），不能重复且快速查找
// 推文列表（链表）有序合并操作
class User {
    public int id;
    public Set<Integer> followed;
    // 用户发布的推文链表头
    public Tweet head;
    static int timestamp;

    public User(int userId) {
        followed = new HashSet<>();
        this.id = userId;
        this.head = null;
        // 关注下自己
        follow(userId);
    }

    public void follow(int userId) {
        followed.add(userId);
    }

    public void unfollow(int userId) {
        if (userId != this.id) {
            followed.remove(userId);
        }
    }

    public void post(int tweetId) {
        Tweet twt = new Tweet(tweetId, timestamp);
        timestamp++;
        // 新建的推文插入链表头
        // 越靠前的推文 time 值越大
        twt.next = head;
        head = twt;
    }
}