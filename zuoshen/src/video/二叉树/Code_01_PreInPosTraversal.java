package video.二叉树;

/**
 * 实现二叉树的先序、中序、后序遍历，包括递归方式和非递归 方式
 */

import java.util.Stack;

public class Code_01_PreInPosTraversal {
    private static void preOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        System.out.print(head.value + " ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);

    }

    private static void inOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        inOrderRecur(head.left);
        System.out.print(head.value + " ");
        inOrderRecur(head.right);
    }

    private static void posOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        posOrderRecur(head.left);
        posOrderRecur(head.right);
        System.out.print(head.value + " ");
    }

    private static void preOrderUnRecur(Node head) {
        System.out.print("pre-unorder: ");
        Stack<Node> stack = new Stack<>();
        if (head == null) return;
        stack.push(head);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.print(node.value + " ");
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        System.out.println();
    }

    private static void inOrderUnRecur(Node head) {
        System.out.print("in-unorder: ");
        if (head == null) return;
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || head != null) {
            if(head !=null){
                stack.push(head);
                head = head.left;
            }else {
                head= stack.pop();
                System.out.print(head.value+" ");
                head = head.right;
            }

        }
        System.out.println();
    }

    //前序：中左右（先右入栈，再左）
    //后序： 左右中===》中右左==>前序
    private static void posOrderUnRecur1(Node head) {
        System.out.print("pos-unorder: ");
        Stack<Node> stack = new Stack<>();
        Stack<Node> print = new Stack<>();
        if(head == null) return;
        stack.push(head);
        while (!stack.isEmpty()){
            head = stack.pop();
            print.push(head);
            if(head.left != null){
                stack.push(head.left);
            }
            if(head.right !=null){
                stack.push(head.right);
            }
        }
        while (!print.isEmpty()){
            System.out.print(print.pop().value+" ");
        }
        System.out.println();
    }


    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }



    public static void main(String[] args) {
        Node head = new Node(5);
        head.left = new Node(3);
        head.right = new Node(8);
        head.left.left = new Node(2);
        head.left.right = new Node(4);
        head.left.left.left = new Node(1);
        head.right.left = new Node(7);
        head.right.left.left = new Node(6);
        head.right.right = new Node(10);
        head.right.right.left = new Node(9);
        head.right.right.right = new Node(11);

        // recursive
        System.out.println("==============recursive==============");
        System.out.print("pre-order: ");
        preOrderRecur(head);
        System.out.println();
        System.out.print("in-order: ");
        inOrderRecur(head);
        System.out.println();
        System.out.print("pos-order: ");
        posOrderRecur(head);
        System.out.println();

        // unrecursive
        System.out.println("============unrecursive=============");
        preOrderUnRecur(head);
        inOrderUnRecur(head);
        posOrderUnRecur1(head);
//        posOrderUnRecur2(head);

    }



}
