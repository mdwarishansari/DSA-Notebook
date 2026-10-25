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
    public ListNode reverseKGroup(ListNode head, int k) {

        // Dummy node simplifies handling the first group
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // Points to the node before the current group
        ListNode groupPrev = dummy;

        while (true) {

            // Find the kth node of the current group
            ListNode kth = groupPrev;

            for (int i = 0; i < k && kth != null; i++) {
                kth = kth.next;
            }

            // Fewer than k nodes remain; leave them unchanged
            if (kth == null) {
                break;
            }

            // Save the first node after the current group
            ListNode groupNext = kth.next;

            // Reverse the group, connecting it to the next group
            ListNode prev = groupNext;
            ListNode curr = groupPrev.next;

            while (curr != groupNext) {

                // Save the next node before reversing the link
                ListNode nextNode = curr.next;

                // Reverse the current node's pointer
                curr.next = prev;

                // Move forward
                prev = curr;
                curr = nextNode;
            }

            // The original first node is now the group's last node
            ListNode oldGroupHead = groupPrev.next;

            // Connect the previous part to the reversed group
            groupPrev.next = kth;

            // Prepare for the next group
            groupPrev = oldGroupHead;
        }

        // Return the actual head, skipping the dummy node
        return dummy.next;
    }
}

