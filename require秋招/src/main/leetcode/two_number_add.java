import javax.xml.bind.ValidationEventLocator;

public class two_number_add {

    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            if (l1 == null) return l2;
            if (l2 == null) return l1;
            ListNode list1 = new ListNode(0);
            ListNode last = list1;
            int carry = 0;
            while (l1 != null || l2 != null) {
                int val1 = 0;
                if (l1 != null) {
                    val1 = l1.val;
                    l1 = l1.next;
                }
                int val2 = 0;
                if (l2 != null) {
                    val2 = l2.val;
                    l2 = l2.next;
                }
                int sum =val1 + val2 + carry;
                carry = sum/ 10;
                last.next = new ListNode(sum % 10);
                last = last.next;

            }
            if (carry >0) {
                last.next = new ListNode(carry);
                last = last.next;
            }
            return list1.next;
        }
    }

}
