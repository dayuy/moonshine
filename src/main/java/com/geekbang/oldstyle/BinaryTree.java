package com.geekbang.oldstyle;

/**
 * 二叉树
 * ：回溯、动态规划、分治算法 都可以归结为树的问题
 * 树永远离不开 树的递归遍历框架
 * void traverse(TreeNode root){
 *      do... 前序遍历
 *     traverse(root.left);
 *     do... 中序遍历
 *     traverse(root.right);
 *     do... 后序遍历
 * }
 *
 *
 * 例：快速排序 就是树的前序遍历
 *            先寻找一个分界点p，再交换元素使得小于num[p]的都在左边，大于的都在右边。再如此重复遍历
 *            sort(int[] nums, int lo, int hi){
 *                //前序遍历位置
 *                int p = partition(nums, lo, hi); // 通过交换元素使得小于num[p]的都在左边，大于的都在右边。构造出临界点p
 *                sort(nums, lo, p-1);
 *                sort(nums, p+1, hi);
 *            }
 * 例：归并排序 就是树的后续遍历
 *            先分到最小，再把单独的两个有序的子数组合并，如此重复整个数组就排序好了
 *            sort(int[] nums, int lo, int hi){
 *                int mid = (lo + hi) / 2;
 *                sort(nums, lo, mid);
 *                sort(nums, mid + 1, hi);  // 先分到最小（树的最底层）
 *                // 后序遍历位置
 *                merge(nums, lo, mid, hi); // 将两个最小的有序数组合并
 *            }
 * **/
public class BinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode subL = root.left = new TreeNode(7);
        TreeNode subR = root.right = new TreeNode(2);
        subL.left = new TreeNode(9);
        subL.right = new TreeNode(6);
        subR.left = new TreeNode(3);
        subR.right = new TreeNode(1);

        root = reverse(root);
        System.out.println(root);
    }

    /**
     * 写递归的关键是要明确函数的「定义」是什么，利用这个「定义」推导最终结果，而不是跳进每一步的细节
     * 明确：当前root节点
     *      「该做什么」写什么能都实现效果
     *      「什么时候做」应该放到前序、中序、或后续位置
     * **/
    // 计算一颗树有几个结点
    public int count(TreeNode root) {
        if (root == null) return 0;
        return 1 + count(root.left) + count(root.right);
    }
    // 1、反转二叉树
    public static TreeNode reverse(TreeNode root) {
        // base case
        if (root == null) return null;
        /**前序遍历**/
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        reverse(root.left);
        reverse(root.right);
        return root;
    }
    // 2、填充二叉树节点的右侧指针
    // 析：如何把题目的要求细化为每个节点需要做的事情，但是只依赖一个节点的话，肯定没法连接「跨父节点」的两个相邻节点
    public static void connect(TreeNode left, TreeNode right) {
        if (left == null || right == null) return;
        // left.left.next = left.right; 这里还是陷入了细节，
        // left.right.next = right.left;
        // right.left.next = right.right;
        // right.right.next = null;
        left.next = right;
        connect(left.left, left.right);
        connect(right.left, right.right);
        connect(left.right, right.left);
    }

    // 3、将二叉树展开为链表
    /** （定位到最低层节点看问题）
     * 1、将root的左子树和右子树拉平后
     * 2、将root的右子树接到左子树的下方，后将整个左子树作为右子树
     * **/
    public static void flatten(TreeNode root) {
        if (root == null) return;
        flatten(root.left);
        flatten(root.right);
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = null;
        root.right = left;

        TreeNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        p.right = right;
    }

    /**
     * 4、给定一个[3,2,1,6,0,5],构建：
     *      a、二叉树的根是数组中最大的元素
     *      b、左子树是通过数组中最大左边部分构造出的最大二叉树
     *      c、右子树是通过数组中最大右边部分构造出的最大二叉树
     */
    public static TreeNode constructMaximumBinaryTree(int[] nums, int start, int end) {
        if (start > end) return null;

        // 找到数组中最大值
        int index = -1, maxVal = Integer.MIN_VALUE;
        for (int i = start; i < end; i++) {
            if (maxVal < nums[i]) {
                index = i;
                maxVal = nums[i];
            }
        }

        TreeNode root = new TreeNode(maxVal);
        root.left = constructMaximumBinaryTree(nums, start, index -1);
        root.right = constructMaximumBinaryTree(nums, index + 1, end);
        return root;
    }
}







































