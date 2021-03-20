package video.二叉树;


public class Code_06_IsBalancedTree {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static class ReturnData {
        public boolean isbalace;
        public int h;

        public ReturnData(boolean isbalace, int h) {
            this.isbalace = isbalace;
            this.h = h;
        }
    }

    //二叉树相当于遍历一个节点3次
    public static ReturnData process(Node node) {
        if (node == null) return new ReturnData(true, 0);

        //左树信息
        ReturnData left = process(node.left);
        if (!left.isbalace) return new ReturnData(false, 0);

        //右树信息
        ReturnData right = process(node.right);
        if (!right.isbalace) return new ReturnData(false, 0);

        if(Math.abs(left.h-right.h)>1)return new ReturnData(false,0);

        return new ReturnData(true,Math.abs(left.h-right.h));

    }

    private static boolean isBalance(Node head) {
        return process(head).isbalace;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        System.out.println(isBalance(head));

    }


}
