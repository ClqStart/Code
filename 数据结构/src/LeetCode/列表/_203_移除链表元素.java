package LeetCode.列表;

import LeetCode.列表.ListNode;
//https://leetcode-cn.com/problems/remove-linked-list-elements/


public class _203_移除链表元素{
    class Solution {
        public ListNode removeElements(ListNode head, int val) {
            while(head!=null&&head.val==val){
                head=head.next;
            }
            if(head==null)
                return head;
            ListNode prev=head;
            while(prev.next!=null){
                if(prev.next.val==val){
                    prev.next=prev.next.next;
                }else{
                    prev=prev.next;
                }
            }
            return head;
        }
    }
}




