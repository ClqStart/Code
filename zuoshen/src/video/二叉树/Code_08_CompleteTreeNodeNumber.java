package video.二叉树;


//递归 1、判断跟节点的右边是不是树的最大高度，是，左边是满的2^(h-l)-1+1+递归遍历（head.right）
//  2、判断跟节点的右边没有达到树的最大高度，右边的高度是满二叉树2^(h-l-1)+递归左边（head.left）
public class Code_08_CompleteTreeNodeNumber {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static int nodeNum(Node head) {
        if (head == null) {
            return 0;
        }
        return bs(head, 1, mostLeftLevel(head, 1));
    }

    //h是一个固定的值
    public static int bs(Node node, int l, int h) {
        if (l == h) {
            return 1;
        }
        if (mostLeftLevel(node.right, l + 1) == h) {
            return 1 << (h - l) + bs(node.right, l + 1, h);
        } else {
            return 1 << (h - l - 1) + bs(node.left, l + 1, h);
        }

    }

    public static int mostLeftLevel(Node node, int level) {
        while (node != null) {
            level++;
            node = node.left;
        }
        return level - 1;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        System.out.println(nodeNum(head));

    }

}
