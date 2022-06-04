package com.geekbang.oldstyle;

import java.util.*;

class UserLibraryInClassPathAppMain {
    public static void main(String[] args) {
        String line = "  This is a   字符串";
        Iterable<String> words = List.of(line.split("-"));
        for(String word: words) {
            System.out.println(word);
        }

        String[] deadends = new String[]{"0201", "0101","0102","1212","2002"};
        String target = "0202";
        int result = BFSMain.openLock(deadends, target);
        System.out.println(result);


        int[] nums = new int[]{1, 2, 2, 2, 3};
        int result2 = BinarySearch.left_bound(nums, 2);
        System.out.println(result2);

        String s = "ADOBECODEBANC", t = "ABC";
        String result3 = SlideWindow.slide_count(s, t);
        System.out.println(result3); // BANC


        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        int profit = stockDeal.maxProfit_k_1(prices);
        System.out.println(profit);
        int profit_varible = stockDeal.maxProfit_k_1_variable(prices);
        System.out.println(profit_varible);
        int profit_with_cool = stockDeal.maxProfit_with_cool(prices);
        System.out.println(profit_with_cool);

        int[] property = new int[]{1,2,3,1};
        int robMoney = houseRobber.rob(property);
        System.out.println(robMoney);
        int robMoney_2 = houseRobber.rob_down_to_up_2(property);
        System.out.println(robMoney_2);


        int[][] intvs = new int[][]{
                {1, 4},
                {3, 6},
                {2, 8},
        };
        int coveredNum = coveredIntervals.removeCovered(intvs);
        System.out.println(coveredNum);

        int[] nsumNums = new int[]{1,3,5,6};
        List<Integer> nSumRes = nSum.twoSum(nsumNums, 9);
        System.out.println(nSumRes);
        int[] nsumNums_n = new int[]{1,1,1,2,2,3};
        List<int[]> nSumRes_n = nSum.twoSum_n(nsumNums_n, 4);
        System.out.println(nSumRes_n);


        int N = 3, W = 4;
        int[] wt = {2, 1, 3};
        int[] val = {4, 2, 3};
        int maxVal = backpack01.knapsack(W, N, wt, val);
        System.out.println(maxVal);


        String nameS = "abcdeffedcba";
        boolean isP = Palindrome.isPalindrome(nameS);
        System.out.println("isPalindrome:" + isP);

        int[] preNumbers = new int[]{-2, 0, 3, -5, 2, -1};
        NumArray numArray = new NumArray(preNumbers);
        numArray.sumRange(1, 4);
    }
}

class BFSMain{
    static String plusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '9')
            ch[j] = '0';
        else
            ch[j] += 1;
        return new String(ch);
    }
    static String minusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '9')
            ch[j] = '8';
        else
            ch[j] -= 1;
        return new String(ch);
    }

    static int openLock(String[] deadends, String target) {
        Set<String> visited = new HashSet<>();
        for (String s : deadends) visited.add(s);

        Queue<String> q = new LinkedList<>();
        int step = 0;
        q.offer("0000");
        visited.add("0000");

        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i=0;i<sz;i++) {
                String cur = q.poll();

                if (cur.equals(target))
                    return step;

                for (int j=0;j<4;j++){
                    String up = plusOne(cur, j);
                    if (!visited.contains(up)) {
                        q.offer(up);
                        visited.add(up);
                    }

                    String down = minusOne(cur, j);
                    if (!visited.contains(down)){
                        q.offer(down);
                        visited.add(down);
                    }
                }
            }
            step++;
        }
        return -1;
    }
}

class BinarySearch{
    static int left_bound(int[] nums, int target) {
        int left = 0;
        int right = nums.length -1;

        while (left<=right) {
            int mid = left + (right - left) /2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] == target) {
                right = mid - 1;
            }
        }
        // 检查left越界情况
        if (left >= nums.length || nums[left] != target) {
            return -1;
        }
        return left;
    }
}

class SlideWindow {
    static String slide_count(String s, String t) {
        HashMap<Character, Integer> need = new HashMap<Character, Integer>();
        HashMap<Character, Integer> window = new HashMap<Character, Integer>();

        for (int i =0;i<t.length();i++) {
            need.put(t.charAt(i), need.getOrDefault(t.charAt(i), 0) + 1);
        }

        int left = 0, right = 0;
        int valid = 0;
        int start = 0, len = Integer.MAX_VALUE;
        while (right< s.length()){
            char c = s.charAt(right);
            right++;
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (need.get(c) == window.get(c)) {
                    valid++;
                }
            }

            while (valid == need.size()) {
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                char d = s.charAt(left);
                left++;
                if (need.containsKey(d)) {
                    if (window.get(d) == need.get(d)) {
                        valid--;
                    }
                    window.put(d, window.getOrDefault(d, 0) - 1);
                }
            }
        }

        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }
}

class stockDeal {
    static int maxProfit_k_1(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i=0;i<n;i++) {
            if (i-1 == -1) {
                dp[0][0] = 0;
                dp[0][1] = -prices[i];
                continue;
            }
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], -prices[i]);
        }
        return dp[n-1][0];
    }

    static int maxProfit_k_1_variable(int[] prices) {
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int i=0;i<n;i++) {
            if (i-1 == -1) {
                dp_i_0 = 0;
                dp_i_1 = -prices[i];
            }
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, -prices[i]);
        }
        return dp_i_0;
    }

    static int maxProfit_with_cool(int[] prices) {
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        int dp_pre_0 = 0; // 代表dp[i-2][0]
        for (int i=0;i<n;i++) {
            int tmp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, dp_pre_0 - prices[i]);
            dp_pre_0 = tmp;
        }
        return dp_i_0;
    }

    static int maxProfit_with_fee(int[] prices, int fee) {
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MAX_VALUE;
        for (int i=0;i<n;i++) {
            int tmp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, tmp - prices[i] - fee);
        }
        return dp_i_0;
    }

    static int maxProfit_k_2(int[] prices) {
        int n = prices.length;
        int max_k = 2;
        int[][][] dp = new int[n][max_k+1][2];
        for (int i=0;i<n;i++) {
            for (int k = max_k; k>=1;k--){
                if (i - 1== -1){
                    dp[i][k][0] = 0;
                    dp[i][k][1] = Integer.MIN_VALUE;
                }
                dp[i][k][0] = Math.max(dp[i-1][k][0], dp[i-1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i-1][k][1], dp[i-1][k - 1][0] - prices[i]);
            }
        }
        return dp[n-1][max_k][0];
    }
}

class houseRobber {
    private static int[] memo; // 2

    static int rob(int[] nums) {
        memo = new int[nums.length]; // 2
        Arrays.fill(memo, -1); // 2
        return dp(nums, 0);
    }

    static int dp(int[] nums, int start) {
        int n = nums.length;
        if (start >= n) return 0;
        if (memo[start] != -1) return memo[start]; // 2
        int res = Math.max(nums[start] + dp(nums, start + 2), dp(nums, start + 1));
        memo[start] = res; // 2
        return res;
    }


    static int rob_down_to_up(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n+2];
        for(int i=n-1;i>=0;i--) {
            dp[i] = Math.max(nums[i] + dp[i+2], dp[i+1]);
        }
        return dp[0];
    }

    static int rob_down_to_up_2(int[] nums) {
        int n = nums.length;
        int dp_i = 0, dp_1 = 0, dp_2 = 0;
        for (int i=n-1;i>=0;i--) {
            dp_i = Math.max(nums[i] + dp_2, dp_1);
            dp_2 = dp_1;
            dp_1 = dp_i;
        }
        return dp_i;
    }
}

class coveredIntervals {
    static int removeCovered(int[][] intvs) {
        Arrays.sort(intvs, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });

        int left = intvs[0][0];
        int right = intvs[0][1];
        int res = 0;

        for (int i=0;i<intvs.length;i++) {
            int[] intv = intvs[i];
            if (left <= intv[0] && right >= intv[1]) {
                res++;
            }
            if (right >= intv[0] && right <= intv[1]) {
                right = intv[1];
            }
            if (right < intv[0]) {
                left = intv[0];
                right = intv[1];
            }
        }

        return intvs.length - res;
    }
}

class nSum {
    // 找到数组中，任意两个数之和为target
    // 利用左右指针
    static List<Integer> twoSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<Integer> res = new ArrayList<Integer>();
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum < target) {
                left++;
            } else if (sum > target) {
                right++;
            } else if (sum == target) {
                res.add(nums[left]);
                res.add(nums[right]);
                return res;
            }
        }
        return res;
    }

    static List<int[]> twoSum_n(int[] nums, int target) {
        Arrays.sort(nums);
        List<int[]> res = new ArrayList<>();
        int left = 0, right = nums.length -1;
        while (left<right) {
            int sum = nums[left] + nums[right];
            int l = nums[left], r = nums[right];
            if (sum < target) {
                while (left<right && nums[left] == l) left++;
            }else if(sum>target){
                while (left<right && nums[right] == r) right--;
            }else{
                int[] d = new int[]{l, r};
                res.add(d);
                while (left<right && nums[left] == l) left++;
                while (left<right && nums[right] == r) right--;
            }
        }
        return res;
    }
}

class TreeNode<T> {
    TreeNode left;
    TreeNode right;
    TreeNode next;
    int val;

    public TreeNode(int val) {
        this.val = val;
    }
}

class binaryTree002{

    static TreeNode constructMaximumBinaryTree(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    static TreeNode build(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int index = -1, maxVal = Integer.MIN_VALUE;
        for (int i = 0;i<nums.length;i++) {
            if (nums[i] > maxVal) {
                maxVal = nums[i];
                index = i;
            }
        }
        TreeNode root = new TreeNode(maxVal);
//        root.left = build(nums, 0, index - 1);
//        root.right = build(nums, index + 1, nums.length - 1);

        return root;
    }
}

class backpack01 {
    // 0-1背包问题
    /**
     * 一个可装重量为 W 的背包和 N 个物品，问最多能装的价值是多少？其中第i个物品的重量为 wt[i]，价值为 val[i]。
     * 分析：
     * 1。没有排序之类的技巧，只能穷举所有可能
     * 2。确定状态：「背包的容量 W」、「装了 N 个物品」。所以最终记录 dp[N][W] 刚好是要解的状态
     * 3。择优：对于每个物品，比较两种选择「装入」、「不装入」。（一定要根据上个物品的选择写表达式。寻找当前与上个的关系）
     * **/
    static int knapsack(int W, int N, int[] wt, int[] val) {
        int[][] dp = new int[N + 1][W + 1];
        for (int i = 1; i<=N; i++) {
            for (int w = 1; w<=W; w++) {
                if (w < wt[i-1]) {
                    // 这种情况下只能选择不装入背包
                    dp[i][w] = dp[i-1][w];
                } else {
                    // 装入或不装入背包，择优
                    dp[i][w] = Math.max(dp[i-1][w], dp[i-1][w - wt[i-1]] + val[i-1]);
                }
            }
        }
        return dp[N][W];
    }
}

// 递归反转链表
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
class reverseChainsaw {
    public void main(String[] args) {
        ListNode[] table = new ListNode[6];
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        table[0] = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;

        System.out.println(table);
        ListNode reversedTable = reverse_all(n1);
        System.out.println(reversedTable);
    }
    /**
     * 反转整个链表
     * 输入 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null
     * 输出 6 -> 5 -> 4 -> 3 -> 2 -> 1 -> null
     * **/
    ListNode reverse_all(ListNode head) {
        if (head.next == null) return head; // base case
        ListNode last = reverse_all(head.next);
        // 后续遍历：可以想象只针对其中一个结点，需要做哪些操作？
        //        1、 需要指向这个结点的箭头 为它原来的next。
        //        2、这个结点的指向 为null
        head.next.next = head; // n1 结点的下一个结点
        head.next = null;
        return last;
    }

    /**
     * 反转链表前n个节点反转
     * 输入 1 -> 2 -> 3 -> 4 -> 5 -> 6
     * 输出 3 -> 2 -> 1 -> 4 -> 5 -> 6
     * **/
    private ListNode successor = null; // 记录下后驱结点
    // 反转以head为起点的n个结点，返回新的头结点。
    ListNode reverse_first_n(ListNode head, int n) {
        if (n == 1) {
            successor = head.next; // 记录最后一个结点，便于最新的头结点指向它
            return head;
        }
        ListNode last = reverse_first_n(head.next, n - 1);
        head.next.next = head;
        head.next = successor;
        return last;
    }

    /**
     * 反转链表的一部分 [m,n]
     *  输入 1 -> 2 -> 3 -> 4 -> 5 -> 6
     *  输出 1 -> 2 -> 5 -> 4 -> 3 -> 6
     * 1、当 m = 1，也就是反转的前n个元素，相当于 reverse_first_n
     * 2、当 m != 1，如果head视为1，想从m个元素反转，
     *              则head.next为 m-1;
     *              head.next.next为 m-1-1;
     * **/
    ListNode reverse_between(ListNode head, int m, int n) {
        // base case
        if (m == 1) {
            return reverse_first_n(head, n);
        }
        // 前进到反转的起点 触发base case
        head.next = reverse_between(head.next, m - 1, n-1);
        return head;
    }


    /**
     * 上面是递归反转，如何迭代反转链表
     * 例：k个节点一组进行反转
     * 输入：1 -> 2 -> 3 -> 4 -> 5
     * k=2 输出：2 -> 1 -> 4 -> 3 -> 5
     * k=3 输出：3 -> 2 -> 1 -> 4 -> 5
     * 分析：链表具有递归性质
     *      1、比如对这个链表调用reverseKGroup(head,2），即2个节点为一组反转链表
     *      2、如果处理前2个节点，后面也是一条链表且长度比较小，这就叫子问题
     * **/
    // 反转以a为头结点的链表
    ListNode reverse(ListNode a) {
        ListNode pre, cur, nxt;
        pre = null; cur = a; nxt = a;
        while (cur != null) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }
    // 反转以a为头结点，到b之间的结点: [a, b)
    ListNode reverse_a_b(ListNode a, ListNode b) {
        ListNode pre, cur, nxt;
        pre = null; cur = a; nxt = a;
        while (cur != b) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }
    // k个结点一组 反转
    ListNode reverse_k_group(ListNode head, int k) {
        if (head == null) return null;
        ListNode a,b;
        a = head; b = head;
        // a 到 b,数k个为一组
        for (int i = 0; i < k; i++) {
            // 不足 k 个，不需要反转，base case
            if (b == null) return head;
            b = b.next;
        }
        // 反转 [a, b) 这k个元素
        ListNode newHead = reverse_a_b(a, b);
        a.next = reverse_k_group(b, k);
        return newHead;
    }

    // 合并两个有序链表
    ListNode combine_2_list(ListNode l1, ListNode l2) {
        // 「虚拟头结点」技巧，是链表算法中常见的技巧，dump节点占位符，避免处理空指针的情况，降低代码复杂性。
        ListNode p = new ListNode(-1), dump = p;
        // ListNode p1 = l1, p2 = l2; 完善：遍历前copy下，防止改变原始数据
        while (l1 != null && l2 != null) {
            if (l1.val >= l2.val) {
                p.next = l2;
                l2 = l2.next;
            } else if (l1.val < l2.val) {
                p.next = l1;
                l1 = l1.next;
            }
            p = p.next;
        }

        if (l1 != null) {
            p.next = l1;
        }
        if (l2 != null) {
            p.next = l2;
        }

        return dump.next;
    }

    // ？？？合并 k 个有序链表
    // 如何快速找到k个节点中最小的节点，接到结果链表上？
    // 「优先级队列（二叉堆）」，把链表节点放入一个最小堆，就可以每次获得k个节点中的最小节点
    ListNode combine_k_list(ListNode[] lists) {
        if (lists.length == 0) return null;
        ListNode dump = new ListNode(-1);
        ListNode p = dump;

        // 优先队列 最小堆
        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, (a, b)->(a.val - b.val));

        for (ListNode head : lists) {
            if (head != null) {
                pq.add(head);
            }
        }

        while (!pq.isEmpty()) {
            // 获取最小节点，接到节点链表中
            ListNode node = pq.poll();
            p.next = node;
            if (node.next != null) {
                pq.add(node.next);
            }
            p = p.next;
        }

        return dump;
    }

    // 返回链表的倒数第k个元素（只遍历一遍）
    ListNode findFromEnd(ListNode head, int k) {
        ListNode first = head, last = head;
        int p1 = 0;
        while (head.next != null) {
            if (p1 >= k) {
                last = last.next;
            }
            first = first.next;
            p1++;
        }
        return last;
    }

    ListNode findFromEnd_2(ListNode head, int k) {
        ListNode p1 = head, p2 = head;
        for (int i = 0; i < k; i++) {
            p1 = p1.next;
        }
        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }

        return p2;
    }

    // 删除倒数第 n 个节点
    ListNode removeNthFromEnd(ListNode head, int n) {
        // 使用虚拟节点 防止出现空指针（如只有5个节点时，删除倒数第五个节点）
        ListNode dump = new ListNode(-1);
        dump.next = head;
        ListNode nth = findFromEnd_2(dump, n+1);
        nth.next = nth.next.next;
        return dump.next;
    }

    // 链表的中点 使用快慢指针
    ListNode middleNode(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    // 判断链表是不是闭环
    boolean hasCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    // 计算环的起点。 既然是环了，为啥还有起点之分
    ListNode detectCycyle(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                break;
            }
        }
        // 说明遇到空指针，表明没有环
        if (fast == null || fast.next == null) {
            return null;
        }

        // 重新指向头结点
        slow = head;
        // 快慢指针同步进行，相交的点就是起点
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }


    // 两条链表是否相交，如何相交，返回相交的节点，没有则返回null
    // 要求：不需要额外的空间，只使用两个指针
    // 难点：由于两条链表长度不同，两条链表之间的节点无法对应
    // 解法1：两条链表分别首尾连接另一条链表，构成长度相同，就可以比较对应节点了
    // 解法2：两条链表分别倒叙，就可以一一对应比较了，而不用顾及长度
    ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA, p2 = headB;
        while (p1 != null && p1.next != null) {
            if (p1.next == null) {
                p1.next = headB;
            }
            p1 = p1.next;

            if (p2.next == null) {
                p2.next = headA;
            }
            p2 = p2.next;

            if (p1 == p2) {
                return p1;
            }
        }
        return null;
    }

    ListNode getIntersectionNode_v2(ListNode headA, ListNode headB) {
        ListNode p1 = inverseListNode(headA);
        ListNode p2 = inverseListNode(headB);
        ListNode res = null;
        while (p1 == p2) {
            p1 = p1.next;
            p2 = p2.next;
            res = p1;
        }
        return res;
    }

    ListNode inverseListNode(ListNode head) {
        ListNode pre, cur, nxt;
        pre = null; cur = head; nxt = head;
        while (cur != null) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }

}


/**
 * 判断回文链表
 * **/
class Palindrome {
    public static void main(String[] args) {
        String nameS = "abcdeffedcba";
        boolean isP = isPalindrome(nameS);
        System.out.println(isP);
    }

    // 1、判断一个字符串是不是回文串；利用「双指针技巧」
    public static boolean isPalindrome(String s) {
        int left = 0, right = s.length() -1;
        while (left < right) {
            System.out.println("left:" + left + " right:" + right);
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            System.out.println(s.charAt(left));
            System.out.println(s.charAt(right));
            left++; right--;
        }
        return true;
    }

    // 左右指针反转数组
    void reverseString(char[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left--;
            right--;
        }
    }

    /**
     * 2、判断一个链表是不是回文链表
     * 析：单链表无法倒着遍历，也就无法使用双指针。
     * 解1：将原始链表反转存入一条新的链表，然后比较两条链表是否相同
     * 解2：借助二叉树后续遍历思路，不反转链表也可以倒叙遍历链表
     */
    private static ListNode left;
    public static boolean isPalindromeChain(ListNode head) {
        left = head;
        return traverse(head);
    }

    public static boolean traverse(ListNode right) {
        if (right == null){
            return true;
        }
        boolean res = traverse(right.next);
        res = res && (right.val == left.val);
        left = left.next;
        return res;
    }
}


/**
 * debug 的方法
 * 递归函数：直接在递归函数内部打印关键值，配合缩进，直观的观察递归函数执行情况
 * **/
class Debugger{
    int count = 0;

    void printIndent(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println("  ");
        }
    }

    int dp(String ring, int i, String key, int j) {
        printIndent(count++);
        System.out.printf("j = %d, j = %d\n", i, j);

        if (j == key.length()) {
            printIndent(--count);
            System.out.printf("return 0\n");
            return 0;
        }

        int res = Integer.MAX_VALUE;
//        for (int k : key.charAt(j)) {
//            res = Math.min(res, dp(ring, j, key, i+1));
//        }

        printIndent(--count);
        System.out.printf("return %d\n", res);
        return res;
    }
}


/**
 * 数组题目：
 * 1、前缀和数组：给定一个整数数组，求出数组从索引 i 到 j 范围内元素的总和，包含i j两点
 * **/
class NumArray {
    private int[] preSum;

    public NumArray(int[] nums) {
        preSum = new int[nums.length + 1];
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i-1] + nums[i-1];
        }
    }

    public int sumRange(int left, int right) {
        return preSum[right] - preSum[left];
    }
}






























