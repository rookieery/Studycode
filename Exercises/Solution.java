package Exercises;
public class Solution {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode reverseList(ListNode head) {
        ListNode reverseHead = null;
        if(head != null) {
            ListNode pre = head;
            ListNode cur = head.next;
            ListNode las = cur;
            if(cur == null) {
                reverseHead = head;
            }
            while (cur != null) {
                if (cur.next == null) {
                    reverseHead = cur;
                }
                head.next = cur.next;
                cur = cur.next;
                las.next = pre;
                pre = las;
                las = cur;
            }
        }
        return reverseHead;
    }
}

