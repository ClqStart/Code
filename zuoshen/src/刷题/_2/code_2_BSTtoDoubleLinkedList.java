package 刷题._2;

import java.util.LinkedList;
import java.util.Queue;

// 二叉搜索树转换成双向列表
public class code_2_BSTtoDoubleLinkedList {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static class Info {
        private Node start;
        private Node end;

        public Info(Node start, Node end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void inOrderToQueue(Node head, Queue<Node> queue) {
      if(head ==null){
          return;
      }
      inOrderToQueue(head.left,queue);
      queue.offer(head);
      inOrderToQueue(head.right,queue);
    }

    public static Node convert1(Node head) {
        if (head == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        inOrderToQueue(head,queue);

        head = queue.poll();
        Node pre = head;
        pre.left = null;
        Node cur = null;
        while (!queue.isEmpty()){
            cur = queue.poll();
            cur.left = pre;
            pre.right = cur;
            pre = cur;
        }
        return head;
    }

    public static Node convert2(Node head) {
        if (head == null) {
            return null;
        }
        return process(head).start;
    }

    public static Info process(Node X) {
        if (X == null) {
            return new Info(null, null);
        }
        Info leftInfo = process(X.left);
        Info rightInfo = process(X.right);
        //判断是不是头尾节点，看看是否需要串起来；
        if (leftInfo.end != null) {
            leftInfo.end.right = X;
        }
        if (rightInfo.start != null) {
            rightInfo.start.left = X;
        }
        //连接
        X.left = leftInfo.end;
        X.right = rightInfo.start;
        return new Info(
                leftInfo.start != null ? leftInfo.start : X,
                rightInfo.end != null ? rightInfo.end : X);
    }

    public static void printBSTInOrder(Node head) {
        System.out.print("BST in-order: ");
        if (head != null) {
            inOrderPrint(head);
        }
        System.out.println();
    }

    public static void inOrderPrint(Node head) {
        if (head == null) {
            return;
        }
        inOrderPrint(head.left);
        System.out.print(head.value + " ");
        inOrderPrint(head.right);
    }

    public static void printDoubleLinkedList(Node head) {
        System.out.print("Double Linked List: ");
        Node end = null;
        while (head != null) {
            System.out.print(head.value + " ");
            end = head;
            head = head.right;
        }
        System.out.print("| ");
        while (end != null) {
            System.out.print(end.value + " ");
            end = end.left;
        }
        System.out.println();
    }


    public static void main(String[] args) {
        Node head = new Node(5);
        head.left = new Node(2);
        head.right = new Node(9);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.left.right.right = new Node(4);
        head.right.left = new Node(7);
        head.right.right = new Node(10);
        head.left.left = new Node(1);
        head.right.left.left = new Node(6);
        head.right.left.right = new Node(8);

        printBSTInOrder(head);
        head = convert1(head);
        printDoubleLinkedList(head);

        System.out.println("===============");
        head = new Node(5);
        head.left = new Node(2);
        head.right = new Node(9);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.left.right.right = new Node(4);
        head.right.left = new Node(7);
        head.right.right = new Node(10);
        head.left.left = new Node(1);
        head.right.left.left = new Node(6);
        head.right.left.right = new Node(8);

        printBSTInOrder(head);
        head = convert2(head);
        printDoubleLinkedList(head);
    }

}
