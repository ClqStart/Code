package video;

import video.util.Node;


//列表的荷兰国旗问题
import static video.util.Node.printLinkedList;

public class Code_12_SmallerEqualBigger {
    private static Node listPartition1(Node head1, int num) {
        if (head1 == null || head1.next == null) return head1;
        int count = 0;
        Node first = head1;
        while (first != null) {
            count++;
            first = first.next;
        }
        int[] arr = new int[count];
        int a = 0;
        first = head1;
        while (first != null) {
            arr[a++] = first.value;
            first = first.next;
        }
        int less = -1;
        int more = count;
        int cur = 0;
        while (cur < more) {
            if (arr[cur] < num) {
                swap(arr, ++less, cur++);
            } else if (arr[cur] > num) {
                swap(arr, --more, cur);
            } else {
                cur++;
            }
        }
        a = 0;
        first = head1;
        while (first != null) {
            first.value = arr[a++];
            first = first.next;
        }

        return head1;

    }

    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
    //算法是稳定的
    private static Node listPartition2(Node head1, int num) {
        Node sH = null; // small head
        Node sT = null; // small tail
        Node eH = null; // equal head
        Node eT = null; // equal tail
        Node bH = null; // big head
        Node bT = null; // big tail
        Node next = null; // save next node
        while (head1 != null) {
            next = head1.next;
            head1.next = null;
            if (head1.value < num) {
                if (sH == null) {
                    sH = head1;
                    sT = head1;
                } else {
                    sT.next = head1;
                    sT = sT.next;
                }
            } else if (head1.value == num) {
                if (eH == null) {
                    eH = head1;
                    eT = head1;
                } else {
                    eT.next = head1;
                    eT = eT.next;
                }
            } else {
                if (bH == null) {
                    bH = head1;
                    bT = head1;
                } else {
                    bT.next = head1;
                    bT = bT.next;
                }

            }
            head1 = next;
        }
        if (sT != null) {
            sT.next = eH;
            eT = eT == null ? sT : eT;
        }
        if(eT !=null){
            eT.next = bH;
        }
        return sH != null ? sH : eH !=null? eH : bH;

    }


    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
//        head1 = listPartition1(head1, 5);
        head1 = listPartition2(head1, 5);
        printLinkedList(head1);

    }


}
