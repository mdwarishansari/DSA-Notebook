/**
 * Definition for singly-linked list.
 *
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *
 *     ListNode() {}
 *
 *     ListNode(int val) {
 *         this.val = val;
 *     }
 *
 *     ListNode(int val, ListNode next) {
 *         this.val = val;
 *         this.next = next;
 *     }
 * }
 */

class Solution {

    /**
     * Reverse nodes between positions left and right.
     *
     * Example:
     *
     * 1 -> 2 -> 3 -> 4 -> 5
     *      |---------|
     *
     * becomes
     *
     * 1 -> 4 -> 3 -> 2 -> 5
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {

        // Nothing to reverse.
        if (head == null || left == right) {
            return head;
        }

        //-------------------------------------------------------
        // Dummy node simplifies cases where left == 1
        //-------------------------------------------------------
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        //-------------------------------------------------------
        // Move 'prev' to the node just before 'left'
        //-------------------------------------------------------
        ListNode prev = dummy;

        for (int i = 1; i < left; i++) {
            prev = prev.next;
        }

        //-------------------------------------------------------
        // 'start' is the first node that will be reversed.
        //-------------------------------------------------------
        ListNode start = prev.next;

        //-------------------------------------------------------
        // Reverse exactly (right-left+1) nodes.
        //-------------------------------------------------------
        ListNode reversedHead = reversePart(start, right - left + 1);

        //-------------------------------------------------------
        // Connect first half with reversed part.
        //-------------------------------------------------------
        prev.next = reversedHead;

        //-------------------------------------------------------
        // 'start' became the last node after reversal.
        // Connect it with the remaining list.
        //-------------------------------------------------------
        start.next = nextNodeAfterReverse;

        return dummy.next;
    }

    // Stores the node immediately after the reversed segment.
    private ListNode nextNodeAfterReverse;

    /**
     * Reverses exactly 'count' nodes.
     *
     * Returns the new head of the reversed part.
     */
    private ListNode reversePart(ListNode head, int count) {

        ListNode previous = null;
        ListNode current = head;

        while (count > 0) {

            // Save next node before changing links.
            ListNode next = current.next;

            // Reverse current node.
            current.next = previous;

            // Move pointers forward.
            previous = current;
            current = next;

            count--;
        }

        // Save the remaining list.
        nextNodeAfterReverse = current;

        // Return new head of reversed part.
        return previous;
    }
}