package 刷题._1;



/**
 * 给定一个二叉树的头结点head,路径规定以下三种不同的规定
 * 1、路径必须是头结点出发，到叶节点为止，返回最大的路径和
 * 2、路径可以从任何节点出发，但必须往下走到到达任何节点，返回最大的路径和
 * 3、路径可以从任何节点出发，到任何节点，返回最大的路径和
 */
public class code_7_MaxSumInTree {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int val) {
            value = val;
        }
    }

    public static int maxSum = Integer.MIN_VALUE;


    //1、路径必须是头结点出发，到叶节点为止，返回最大的路径和
    //最后一层进行求和
    public static int maxPath(Node head) {
        maxSum = Integer.MIN_VALUE;
        p(head, 0);
        return maxSum;
    }

    private static void p(Node node, int pre) {
        if (node.left == null && node.right == null) {
            maxSum = Math.max(maxSum, pre + node.value);
            return;
        }
        if (node.left != null) {
            p(node.left, pre + node.value);
        }
        if (node.right != null) {
            p(node.right, pre + node.value);
        }
    }

    //二叉树递归版方法
    //向左右递归二叉树要值加上头结点
    public static int process(Node node) {
        if (node.left == null && node.right == null) {
            return node.value;
        }
        int maxSum1 = Integer.MIN_VALUE;
        if (node.left != null) {
            maxSum1 = process(node.left);
        }
        if (node.right != null) {
            maxSum1 = Math.max(maxSum1, process(node.right));
        }
        return maxSum1 + node.value;
    }
    //2、路径可以从任何节点出发，但必须往下走到到达任何节点，返回最大的路径和

    //所有可能性
    // 1）X无关的时候， 1， 左树上的整体最大路径和 2， 右树上的整体最大路径和
    // 2) X有关的时候 3， x自己 4， x往左走 5，x往右走

    public static int maxSumonPath(Node head) {
        if (head == null) {
            return 0;
        }
        return f(head).allTreeMaxSum;
    }

    public static class info {
        public int allTreeMaxSum;
        public int fromHeadSum;

        public info(int allTreeMaxSum, int fromHeadSum) {
            this.allTreeMaxSum = allTreeMaxSum;
            this.fromHeadSum = fromHeadSum;
        }
    }

    public static info f(Node node) {
        if (node == null) {
            return null;
        }
        info leftInfo = f(node.left);
        info rightInfo = f(node.right);

        int p1 = leftInfo == null ? Integer.MIN_VALUE : leftInfo.allTreeMaxSum;
        int p2 = rightInfo == null ? Integer.MIN_VALUE : rightInfo.allTreeMaxSum;
        int p3 = node.value;
        int p4 = leftInfo == null ? Integer.MIN_VALUE : node.value + leftInfo.fromHeadSum;
        int p5 = rightInfo == null ? Integer.MIN_VALUE : node.value + rightInfo.fromHeadSum;

        int allTreeMaxSum = Math.max(Math.max(Math.max(p1, p2), p3), Math.max(p4, p5));
        int fromHeadSum = Math.max(Math.max(p3, p4), p5);
        return new info(allTreeMaxSum, fromHeadSum);
    }

    //3、路径可以从任何节点出发，到任何节点，返回最大的路径和
    //所有可能性
    // 1）X无关的时候， 1， 左树上的整体最大路径和 2， 右树上的整体最大路径和
    // 2) X有关的时候 3， x自己 4， x往左走 5，x往右走   6,既往左又往右，x作为桥梁
    public static info f2(Node node) {
        if (node == null) {
            return null;
        }
        info leftInfo = f2(node.left);
        info rightInfo = f2(node.right);

        int p1 = leftInfo == null ? Integer.MIN_VALUE : leftInfo.allTreeMaxSum;
        int p2 = rightInfo == null ? Integer.MIN_VALUE : rightInfo.allTreeMaxSum;
        int p3 = node.value;
        int p4 = leftInfo == null ? Integer.MIN_VALUE : node.value + leftInfo.fromHeadSum;
        int p5 = rightInfo == null ? Integer.MIN_VALUE : node.value + rightInfo.fromHeadSum;
        int p6 = rightInfo != null && leftInfo != null ? leftInfo.fromHeadSum+node.value+rightInfo.fromHeadSum: Integer.MIN_VALUE;


        int allTreeMaxSum =Math.max(Math.max(Math.max(Math.max(p1, p2), p3), Math.max(p4, p5)),p6);
        int fromHeadSum = Math.max(Math.max(p3, p4), p5);
        return new info(allTreeMaxSum, fromHeadSum);
    }
    //4、从任何节点到子节点的最大路径和

    public static int AnyToEndSum(Node head){
        int maxSum = Integer.MIN_VALUE;
        if(head.left == null && head.right ==null){
            maxSum = Math.max(maxSum,head.value);
            return head.value;
        }
        int nextMax = 0;
        if(head.left != null){
            nextMax = AnyToEndSum(head.left);
        }
        if(head.right != null){
            nextMax = Math.max(nextMax,AnyToEndSum(head.right));
        }
        int res = head.value+nextMax;
        maxSum = Math.max(res,maxSum);
        return res;

    }
}
