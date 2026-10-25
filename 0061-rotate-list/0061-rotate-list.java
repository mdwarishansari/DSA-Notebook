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
    public ListNode rotateRight(ListNode head, int k) {

        // Handle an empty list or a single-node list
        if (head == null || head.next == null) {
            return head;
        }

        // Step 1: Find the list length and its last node
        int length = 1;
        ListNode tail = head;

        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        // Step 2: Remove unnecessary full rotations
        k = k % length;

        // No rotation is needed
        if (k == 0) {
            return head;
        }

        // Step 3: Temporarily form a circular linked list
        tail.next = head;

        // Step 4: Find the new tail
        // Its zero-based position is length - k - 1
        int steps = length - k - 1;
        ListNode newTail = head;

        for (int i = 0; i < steps; i++) {
            newTail = newTail.next;
        }

        // Step 5: The node after the new tail becomes the new head
        ListNode newHead = newTail.next;

        // Break the circle to restore a normal linked list
        newTail.next = null;

        return newHead;
    }
}
