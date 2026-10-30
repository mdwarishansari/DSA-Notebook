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

import java.util.*;

class Solution {
    public ListNode removeNodes(ListNode head) {

        // Stack stores nodes that should remain in the list.
        Stack<ListNode> st = new Stack<>();

        // Traverse the original linked list.
        ListNode curr = head;

        while (curr != null) {

            // Remove all smaller nodes from the stack.
            // Current node is greater and exists to their right.
            while (!st.isEmpty() &&
                   curr.val > st.peek().val) {

                st.pop();
            }

            // Add the current node to the stack.
            st.push(curr);

            // Move to the next node.
            curr = curr.next;
        }

        // Reconstruct the modified linked list.
        ListNode newHead = null;
        ListNode tail = null;

        while (!st.isEmpty()) {

            // Get the next remaining node.
            ListNode node = st.remove(0);

            // First node becomes the new head.
            if (newHead == null) {
                newHead = node;
                tail = node;
            } else {
                // Connect the previous node to current node.
                tail.next = node;
                tail = node;
            }
        }

        // Ensure the last node points to null.
        if (tail != null) {
            tail.next = null;
        }

        return newHead;
    }
}
