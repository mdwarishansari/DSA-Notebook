
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) {
 *         this.val = val;
 *         this.next = next;
 *     }
 * }
 */

class Solution {
    public ListNode reverseList(ListNode head) {

        // Initially, there is no previous node.
        ListNode prev = null;

        // Start traversing from the head.
        ListNode curr = head;

        while (curr != null) {

            // Save the next node before changing the link.
            ListNode next = curr.next;

            // Reverse the current node's pointer.
            curr.next = prev;

            // Move prev forward to the current node.
            prev = curr;

            // Move curr forward to the saved next node.
            curr = next;
        }

        // Prev now points to the new head.
        return prev;
    }
}
