package video;

import video.util.Node;

import java.util.HashMap;
import java.util.HashSet;

//对于有环和无环的两个单列表，头结点分别是head1和head2,判断两个链表相交的第一个节点，么有返回null，时间复杂度（m+n）


//有环的情况：单列表只能环在最后面
public class Code_14_FindFirstIntersectNode {
    private static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
         //1.判断列表有没有环
//        HashSet<Node> set1 = new HashSet<>();
//        Node first = head1;
//        Node flaghead1 = null;
//        while (first != null) {
//            if (set1.contains(first)) {
//                flaghead1 = first;
//                break;
//            }
//            set1.add(first);
//            first = first.next;
//        }
//
//        HashSet<Node> set2 = new HashSet<>();
//        Node second = head2;
//        Node flaghead2 = null;
//        while (second != null) {
//            if (set2.contains(second)) {
//                flaghead2 = second;
//                break;
//            }
//            set2.add(second);
//            second = second.next;
//        }

        //2、判断相交
        HashSet<Node> set3 = new HashSet<>();
        Node cur = head1;
        while (cur != null) {
            set3.add(cur);
            cur = cur.next;
            if (set3.contains(cur)) break;
        }
        Node cur2 = head2;
        HashSet<Node> set4 = new HashSet<>();
        while (cur2 != null) {
            if (set3.contains(cur2)) {
                return cur2;
            }
            set4.add(cur2);
            cur2 = cur2.next;
            if (set4.contains(cur2)) break;
        }
        return null;

    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);
        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

    }


}
