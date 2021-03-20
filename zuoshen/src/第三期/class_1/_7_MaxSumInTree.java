package 第三期.class_1;


/**
 * 给定一个二叉树的头节点head，路径的规定有以下三种不同的规定：
 * <p>
 * 1）路径必须是头节点出发，到叶节点为止，返回最大路径和
 * <p>
 * 2）路径可以从任何节点出发，但必须往下走到达任何节点，返回最大路径和
 * <p>
 * 3）路径可以从任何节点出发，到任何节点，返回最大路径和
 */
public class _7_MaxSumInTree {

    public static int maxSum = Integer.MIN_VALUE;

    // 1）路径必须是头节点出发，到叶节点为止，返回最大路径和
    public static int maxPath(Node x, int pre) {
        maxSum = Integer.MIN_VALUE;
        prePath(x, 0);
        return maxSum;
    }

    public static void prePath(Node head, int preSum) {
        //只有叶子节点可以接触到maxSum
        if (head.left == null && head.right == null) {
            maxSum = Math.max(maxSum, preSum + head.value);
        }
        if (head.left != null) {
            prePath(head.left, preSum + head.value);
        }
        if (head.right != null) {
            prePath(head.right, preSum + head.value);
        }

    }
    // 1）路径必须是头节点出发，到叶节点为止，返回最大路径和    利用递归套路
    public static int process2(Node head) {
        //将叶节点定位返回的根，不是空节点
        if (head.left == null && head.right == null) return head.value;

        int next = Integer.MIN_VALUE;
        if (head.left != null) {
            next = process2(head.left);
        }
        if(head.right !=null){
            next = Math.max(next,process2(head.right));
        }
        return  next+head.value;

    }

//  2）路径可以从任何节点出发，但必须往下走到达任何节点，返回最大路径和
    /**
     * 与头结点无关，向左右节点要整树最和
     * 与头结点有关，要左右节点的从头最和
     */

    //所有可能性
    // 1）X无关的时候， 1， 左树上的整体最大路径和 2， 右树上的整体最大路径和
    // 2) X有关的时候 3， x自己 4， x往左走 5，x往右走
    public static class Info{
        public int allTreeMaxSum;
        public  int  fromHeadMaxSum;

        public Info(int all,int from){
            allTreeMaxSum = all;
            fromHeadMaxSum = from;
        }

    }

    public static Info pro(Node head){
        if(head ==null){
            return null;
        }
        Info leftInfo = pro(head.left);
        Info  rightInfo = pro(head.right);
        int p1= Integer.MIN_VALUE;
        if(leftInfo !=null){
            p1  =  leftInfo.allTreeMaxSum;
        }

        int p2 = Integer.MIN_VALUE;

        if(rightInfo != null){
            p2 = rightInfo.allTreeMaxSum;
        }

        int p3 = head.value;
        int p4 = Integer.MIN_VALUE;
        if(leftInfo !=null){
            p4 = leftInfo.fromHeadMaxSum+head.value;
        }

        int p5 = Integer.MIN_VALUE;

        if(rightInfo != null){
            p5 = rightInfo.fromHeadMaxSum+head.value;
        }
        int allTreeMaxSum = Math.max(Math.max(Math.max(p1, p2), p3), Math.max(p4, p5));
        int fromHeadSum = Math.max(Math.max(p3, p4), p5);
        return new Info(allTreeMaxSum, fromHeadSum);


    }

    public  static  int maxSum2(Node head){
        if(head == null){
            return 0;
        }
        return  pro(head).allTreeMaxSum;

    }


    //3）路径可以从任何节点出发，到任何节点，返回最大路径和
    //所有可能性
    // 1）X无关的时候， 1， 左树上的整体最大路径和 2， 右树上的整体最大路径和
    // 2) X有关的时候 3， x自己 4， x往左走 5，x往右走   6,既往左又往右，x作为桥梁
    public static class Info2{
        public int allTreeMaxSum;
        public  int  fromHeadMaxSum;

        public Info2(int all,int from){
            allTreeMaxSum = all;
            fromHeadMaxSum = from;
        }

    }

    public static Info2 pro2(Node head){
        if(head ==null){
            return null;
        }
        Info2 leftInfo = pro2(head.left);
        Info2  rightInfo = pro2(head.right);
        int p1= Integer.MIN_VALUE;
        if(leftInfo !=null){
            p1  =  leftInfo.allTreeMaxSum;
        }

        int p2 = Integer.MIN_VALUE;

        if(rightInfo != null){
            p2 = rightInfo.allTreeMaxSum;
        }

        int p3 = head.value;
        int p4 = Integer.MIN_VALUE;
        if(leftInfo !=null){
            p4 = leftInfo.fromHeadMaxSum+head.value;
        }

        int p5 = Integer.MIN_VALUE;

        if(rightInfo != null){
            p5 = rightInfo.fromHeadMaxSum+head.value;
        }
        int p6 =  Integer.MIN_VALUE;
        if(rightInfo!=null && leftInfo !=null){
            p6 = leftInfo.fromHeadMaxSum+ rightInfo.fromHeadMaxSum+head.value;
        }

        int allTreeMaxSum = Math.max(Math.max(Math.max(p1, p2), p3), Math.max(p4, Math.max(p5,p6)));
        int fromHeadSum = Math.max(Math.max(p3, p4), p5);
        return new Info2(allTreeMaxSum, fromHeadSum);


    }

    public  static  int maxSum3(Node head){
        if(head == null){
            return 0;
        }
        return  pro(head).allTreeMaxSum;

    }

    //4、从任何节点到子节点的最大路径和

    //递归只可以根节点和叶子节点跟新max

    public static int AnyToEndSum(Node head){
        //将叶节点定位返回的根，不是空节点
        int max =Integer.MIN_VALUE;
        if (head.left == null && head.right == null){
            max = Math.max(max,head.value);
            return head.value;
        }
        int nextMax = Integer.MIN_VALUE;
        if (head.left != null) {
            nextMax = AnyToEndSum(head.left);
        }
        if(head.right !=null){
            nextMax = Math.max(nextMax,AnyToEndSum(head.right));
        }
        int res = head.value+nextMax;
        max = Math.max(max,res);
        return res;

    }





    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int val) {
            value = val;
        }
    }

}
