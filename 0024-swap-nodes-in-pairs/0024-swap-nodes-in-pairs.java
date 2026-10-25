/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class Solution {
    public ListNode swapPairs(ListNode head) {

        // Dummy node simplifies swapping the first pair
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // Points to the node before the current pair
        ListNode prev = dummy;

        // Continue while at least two nodes remain
        while (prev.next != null && prev.next.next != null) {

            // Identify the two nodes to swap
            ListNode first = prev.next;
            ListNode second = first.next;

            // Step 1: Connect first to the node after second
            first.next = second.next;

            // Step 2: Place second before first
            second.next = first;

            // Step 3: Connect the previous part to second
            prev.next = second;

            // Move to the end of the swapped pair
            prev = first;
        }

        // Return the new head, skipping the dummy node
        return dummy.next;
    }
}
