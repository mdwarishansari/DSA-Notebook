
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
    public ListNode reverseBetween(ListNode head, int left, int right) {

        // Dummy node handles reversal starting at position 1.
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // Move prev to the node just before position left.
        ListNode prev = dummy;
        for (int i = 1; i < left; i++) {
            prev = prev.next;
        }

        // curr remains the first node of the reversing portion.
        ListNode curr = prev.next;

        // Move each next node to the front of the sublist.
        for (int i = 0; i < right - left; i++) {

            // Node that will be moved to the front.
            ListNode temp = curr.next;

            // Remove temp from its current position.
            curr.next = temp.next;

            // Insert temp immediately after prev.
            temp.next = prev.next;
            prev.next = temp;
        }

        // Return the possibly changed head.
        return dummy.next;
    }
}
